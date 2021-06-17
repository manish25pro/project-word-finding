package com.firestudio.projectwordfinding.network;

import com.firestudio.projectwordfinding.modals.Word;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {

@GET("words")
    Call<List<Word>>getSearchResult(@Query("ml") String word);


}
