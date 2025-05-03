package com.brandon.quizy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.brandon.quizy.databinding.ActivityLoginBinding;
import com.brandon.quizy.utils.ExceptionHandler;
import com.brandon.quizy.utils.InputValidator;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialize();
    }

    private void initialize() {
        if (isUserLoggedIn()) {
            startMainActivity();
            return;
        }

        setupUI();
    }

    private boolean isUserLoggedIn() {
        return false; // Always return false since Firebase is removed
    }

    private void setupUI() {
        initBinding();
        setOnClickListener();
    }

    private void initBinding() {
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void setOnClickListener() {
        binding.btnLogin.setOnClickListener(this::login);
        binding.txtSignupLink.setOnClickListener(this::startSignupActivity);
    }

    private Bundle getUserInput() {
        EditText editEmail = binding.editTxtEmail;
        EditText editPassword = binding.editTxtPassword;

        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        Bundle userInput = new Bundle();
        userInput.putString("email", email);
        userInput.putString("password", password);

        return userInput;
    }

    private boolean isInputValid(String email, String password) {
        if (!InputValidator.isEmailValid(email)) {
            Toast.makeText(this, "Invalid email format.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!InputValidator.isPasswordValid(password)) {
            Toast.makeText(this, "Password must be at least 6 characters long.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void startSignupActivity(View v) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
        finish();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void setButtonEnable(boolean isEnabled) {
        View btnLogin = binding.btnLogin;

        btnLogin.setEnabled(isEnabled);

        float alpha = isEnabled ? 1.0f : 0.5f;
        btnLogin.setAlpha(alpha);
    }

    private void login(View v) {
        setButtonEnable(false);

        Bundle input = getUserInput();

        String email = input.getString("email");
        String password = input.getString("password");

        if (!isInputValid(email, password)) {
            return;
        }

        // Removed Firebase login code
        Toast.makeText(this, "Login functionality removed.", Toast.LENGTH_LONG).show();
        setButtonEnable(true);
    }
}
