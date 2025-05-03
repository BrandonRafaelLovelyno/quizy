package com.brandon.quizy.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.brandon.quizy.R;
import com.brandon.quizy.databinding.ActivityCreateQuizBinding;
import com.brandon.quizy.utils.InputValidator;

public class CreateQuizActivity extends AppCompatActivity {
    private ActivityCreateQuizBinding binding;

    @Override
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);

        setupUI();
    }

    private void setupUI() {
        initBinding();
        initToolbar();
        setOnChangeListener();
    }

    private void initBinding() {
        binding = ActivityCreateQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initToolbar() {
        Toolbar toolbar = binding.toolbar.toolbar;
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void setOnChangeListener() {
        EditText editText = binding.editTxtTitle;
        setButtonEnable(false);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setButtonEnable(InputValidator.isQuizTitleValid(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getOnBackPressedDispatcher().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setButtonEnable(boolean isEnabled) {
        View btnLogin = binding.btnCreateQuiz;

        btnLogin.setEnabled(isEnabled);

        float alpha = isEnabled ? 1.0f : 0.5f;
        btnLogin.setAlpha(alpha);
    }
}
