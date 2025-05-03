package com.brandon.quizy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.brandon.quizy.databinding.ActivitySignupBinding;
import com.brandon.quizy.network.model.response.SignupResponse;
import com.brandon.quizy.network.repository.AuthRepository;
import com.brandon.quizy.network.repository.RepositoryCallback;
import com.brandon.quizy.utils.InputValidator;

public class SignupActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;
    private AuthRepository mAuthRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isUserLoggedIn()) {
            startMainActivity();
            return;
        }

        initUI();
        initVariables();
    }

    private void initVariables() {
        mAuthRepo = new AuthRepository();
    }

    private boolean isUserLoggedIn() {
        return false; // Always return false since Firebase is removed
    }

    private void initUI() {
        initBinding();
        setOnClickListener();
    }

    private void initBinding() {
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void setOnClickListener(){
        binding.btnCreateAccount.setOnClickListener(this::signup);
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

    private void onSignupSuccess(SignupResponse response) {
        Toast.makeText(this, "Verification email sent.", Toast.LENGTH_SHORT).show();
    }

    private void onSignupError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void onSignupComplete(){
        setButtonEnable(true);
    }

    private void signup(View v) {
        setButtonEnable(false);

        Bundle input = getUserInput();

        String name = input.getString("name");
        String email = input.getString("email");
        String password = input.getString("password");

        if (!isInputValid(name, email, password)) {
            return;
        }

        RepositoryCallback<SignupResponse> callback = mAuthRepo.makeRepositoryCallback(this::onSignupSuccess, this::onSignupError, this::onSignupComplete);
        mAuthRepo.signup(email, name, password, callback);


    }
}
