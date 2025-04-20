package com.brandon.quizy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.brandon.quizy.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        initBinding();
    }

    private void initBinding() {
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
