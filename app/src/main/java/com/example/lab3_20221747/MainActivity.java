package com.example.lab3_20221747;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab3_20221747.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGoToCounter.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CounterActivity.class));
        });

        binding.btnCheckConnection.setOnClickListener(v -> checkInternetConnection());

        binding.btnSearch.setOnClickListener(v -> {
            String movieId = binding.etMovieId.getText().toString().trim();
            if (movieId.isEmpty()) {
                Toast.makeText(this, "Ingrese un ID de IMDb válido", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
            intent.putExtra("MOVIE_ID", movieId);
            startActivity(intent);
        });
    }

    private void checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isConnected = false;
        if (cm != null) {
            NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
            if (capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))) {
                isConnected = true;
            }
        }

        if (isConnected) {
            Toast.makeText(this, "Conexión a internet exitosa (Success)", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Error: Sin conexión a internet", Toast.LENGTH_LONG).show();
        }
    }
}