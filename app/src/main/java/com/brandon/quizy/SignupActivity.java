package com.brandon.quizy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.brandon.quizy.databinding.ActivitySignupBinding;
import com.brandon.quizy.utils.InputValidator;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    private void initialize() {
        initFirebase();

        if (isUserLoggedIn()) {
            startMainActivity();
            return;
        }

        setupUI();
    }

    private boolean isUserLoggedIn() {
        return mAuth.getCurrentUser() != null;
    }

    private void setupUI() {
        initBinding();
        setOnClickListener();
    }

    private void initBinding() {
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initFirebase(){
        mAuth = FirebaseAuth.getInstance();
    }

    private void setOnClickListener(){
        binding.btnCreateAccount.setOnClickListener(this::createUser);

        binding.txtLoginLink.setOnClickListener(this::startLoginActivity);
    }

    private Bundle getUserInput() {
        EditText editName = binding.editName;
        EditText editEmail = binding.editTxtEmail;
        EditText editPassword = binding.editTxtPassword;

        String name = editName.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        Bundle userInput = new Bundle();
        userInput.putString("name", name);
        userInput.putString("email", email);
        userInput.putString("password", password);

        return userInput;
    }

    private boolean isInputValid(String name, String email, String password) {
        if (!InputValidator.isEmailValid(email)) {
            Toast.makeText(this, "Invalid email format.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!InputValidator.isNameValid(name)) {
            Toast.makeText(this, "Name must contain only letters and spaces.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!InputValidator.isPasswordValid(password)) {
            Toast.makeText(this, "Password must be at least 6 characters long.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void setButtonEnable(boolean isEnabled){
        binding.btnCreateAccount.setEnabled(isEnabled);

        float alpha = isEnabled ? 1.0f : 0.5f;
        binding.btnCreateAccount.setAlpha(alpha);
    }

    private void onCreatedUser(Task<AuthResult> task) {
        if (task.isSuccessful()) {
            startMainActivity();
        } else {
            Toast.makeText(this, "Signup failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void startLoginActivity(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void createUser(View v) {
        setButtonEnable(false);

        Bundle input = getUserInput();

        String name = input.getString("name");
        String email = input.getString("email");
        String password = input.getString("password");

        if (!isInputValid(name, email, password)) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(t -> {
                    onCreatedUser(t);
                    setButtonEnable(true);
                });
    }
}
