package com.example.lab3_20221747;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbService {
    @GET("/")
    Call<Movie> getMovieByImdbId(
            @Query("apikey") String apiKey,
            @Query("i") String imdbId
    );
}