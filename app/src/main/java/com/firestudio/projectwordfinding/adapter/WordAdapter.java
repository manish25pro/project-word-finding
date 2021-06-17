package com.firestudio.projectwordfinding.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firestudio.projectwordfinding.R;
import com.firestudio.projectwordfinding.modals.Word;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordHolder> {
    Context context;
    List<Word> wordList;

    public WordAdapter(Context context, List<Word> wordList) {
        this.context = context;
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public WordAdapter.WordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.word_item_card_design, parent, false);
        return new WordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordAdapter.WordHolder holder, int position) {
        Word word = wordList.get(position);
        holder.word.setText(word.getWord().substring(0,1).toUpperCase()+word.getWord().substring(1));
        holder.score.setText(word.getScore().toString());

    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public class WordHolder extends RecyclerView.ViewHolder {
        TextView word, score;

        public WordHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.word_card);
            score = itemView.findViewById(R.id.score_card);
        }
    }
}
