package com.example.lab3_20221747;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab3_20221747.databinding.ActivityMovieDetailBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailActivity extends AppCompatActivity {

    private ActivityMovieDetailBinding binding;
    private static final String API_KEY = "bf81d461";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String movieId = getIntent().getStringExtra("MOVIE_ID");
        if (movieId != null && !movieId.isEmpty()) {
            fetchMovieDetails(movieId);
        }

        binding.btnBackDialog.setOnClickListener(v -> showExitConfirmationDialog());
    }

    private void fetchMovieDetails(String movieId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OmdbService service = retrofit.create(OmdbService.class);
        service.getMovieByImdbId(API_KEY, movieId).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Movie movie = response.body();
                    if ("True".equalsIgnoreCase(movie.getResponse())) {
                        binding.tvMovieTitle.setText(movie.getTitle());
                        binding.tvMovieYear.setText(movie.getYear());
                    } else {
                        Toast.makeText(MovieDetailActivity.this, "Película no encontrada", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(MovieDetailActivity.this, "Error al conectar con la API OMDB", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setMessage("¿Desea volver al menú principal?")
                .setPositiveButton("Sí", (dialog, which) -> finish())
                .setNegativeButton("No", null)
                .show();
    }
}