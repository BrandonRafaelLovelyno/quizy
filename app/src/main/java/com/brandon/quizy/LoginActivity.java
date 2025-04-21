package com.brandon.quizy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.brandon.quizy.databinding.ActivityLoginBinding;
import com.brandon.quizy.utils.ExceptionHandler;
import com.brandon.quizy.utils.InputValidator;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);

        initBinding();
        initFirebase();
        setOnClickListener();
    }

    private void initBinding() {
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initFirebase() {
        mAuth = FirebaseAuth.getInstance();
    }

    private void setOnClickListener() {
        binding.btnLogin.setOnClickListener(this::login);
        binding.txtSignupLink.setOnClickListener(this::startSignupActivity);
    }

    private Bundle getUserInput() {
        EditText editEmail = binding.editEmail;
        EditText editPassword = binding.editPassword;

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

    private void onLoggedIn(Task<AuthResult> task) {
        if (task.isSuccessful()) {
            startMainActivity();
        } else {
            String errorMessage = ExceptionHandler.getFriendlyErrorMessage(task.getException());
            Toast.makeText(this, "Login failed: " + errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    private void login(View v) {
        setButtonEnable(false);

        Bundle input = getUserInput();

        String email = input.getString("email");
        String password = input.getString("password");

        if (!isInputValid(email, password)) {
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(t -> {
            onLoggedIn(t);
            setButtonEnable(true);
        });
    }
}
