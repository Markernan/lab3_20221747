package com.example.lab3_20221747;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.lab3_20221747.databinding.ActivityCounterBinding;

public class CounterActivity extends AppCompatActivity {

    private ActivityCounterBinding binding;
    private CounterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCounterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(CounterViewModel.class);

        viewModel.getCount().observe(this, count -> {
            binding.tvCounterValue.setText(String.valueOf(count));
        });

        binding.btnStartCounter.setOnClickListener(v -> viewModel.startCounting());
        binding.btnBack.setOnClickListener(v -> finish());
    }
}