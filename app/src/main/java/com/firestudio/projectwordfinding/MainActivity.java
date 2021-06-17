package com.firestudio.projectwordfinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firestudio.projectwordfinding.adapter.WordAdapter;
import com.firestudio.projectwordfinding.modals.Word;
import com.firestudio.projectwordfinding.network.RetrofitClient;
import com.firestudio.projectwordfinding.network.RetrofitInterface;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static String BASEURL = "https://api.datamuse.com/";
    TextInputEditText inputWordEditText;
    Button searchButton;
    RecyclerView recyclerView;
    WordAdapter wordAdapter;
    List<Word> listOfWords;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputWordEditText = findViewById(R.id.enter_word_edit_text);
        searchButton = findViewById(R.id.search_image_button);
        recyclerView = findViewById(R.id.word_list_recyclerview);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);
        //RecyclerView Property
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        listOfWords = new ArrayList<>();
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputWordEditText.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Insert The Word", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    String inputString = inputWordEditText.getText().toString().trim();
                    RetrofitInterface retrofitInterface = RetrofitClient.getRetrofitInstance(BASEURL).create(RetrofitInterface.class);
                    Call<List<Word>> wordList = retrofitInterface.getSearchResult(inputString);
                    wordList.enqueue(new Callback<List<Word>>() {
                        @Override
                        public void onResponse(Call<List<Word>> call, Response<List<Word>> response) {
                            listOfWords = response.body();
                            wordAdapter = new WordAdapter(getApplicationContext(), listOfWords);
                            recyclerView.setAdapter(wordAdapter);
                            progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(Call<List<Word>> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });
    }
}