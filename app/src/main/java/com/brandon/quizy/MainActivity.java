package com.brandon.quizy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.brandon.quizy.databinding.ActivityMainBinding;
import com.brandon.quizy.ui.adapter.QuizAttemptAdapter;
import com.brandon.quizy.ui.decoration.HorizontalSpacingDecoration;
import com.brandon.quizy.ui.decoration.LeftPaddingDecoration;
import com.brandon.quizy.ui.item.QuizAttemptItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();
        initQuizAttemptRecyclerView();
    }

    private void initBinding() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initQuizAttemptRecyclerView() {
        RecyclerView quizAttemptRecyclerView = binding.recentQuizRecyclerView;
        quizAttemptRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<QuizAttemptItem> quizAttemptItems = getQuizAttempts();
        QuizAttemptAdapter quizAttemptAdapter = new QuizAttemptAdapter(quizAttemptItems);

        quizAttemptRecyclerView.setAdapter(quizAttemptAdapter);
        quizAttemptRecyclerView.addItemDecoration(new HorizontalSpacingDecoration(50));
        quizAttemptRecyclerView.addItemDecoration(new LeftPaddingDecoration(this,30));
    }

    private List<QuizAttemptItem> getQuizAttempts() {
        final List<QuizAttemptItem> quizAttemptItems = new ArrayList<>();
        quizAttemptItems.add(new QuizAttemptItem("Object Oriented Programming", "https://res.cloudinary.com/dohewcyes/image/upload/v1743477503/image/quizy/tgdvx5ubqt58yujtsult.png", 100, 1, 10));
        quizAttemptItems.add(new QuizAttemptItem("Object Oriented Programming", "https://res.cloudinary.com/dohewcyes/image/upload/v1743477503/image/quizy/tgdvx5ubqt58yujtsult.png", 100, 1, 10));
        quizAttemptItems.add(new QuizAttemptItem("Object Oriented Programming", "https://res.cloudinary.com/dohewcyes/image/upload/v1743477503/image/quizy/tgdvx5ubqt58yujtsult.png", 100, 1, 10));
        quizAttemptItems.add(new QuizAttemptItem("Object Oriented Programming", "https://res.cloudinary.com/dohewcyes/image/upload/v1743477503/image/quizy/tgdvx5ubqt58yujtsult.png", 100, 1, 10));

        return quizAttemptItems;
    }
}
