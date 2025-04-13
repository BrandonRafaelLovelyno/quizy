package com.brandon.quizy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.brandon.quizy.databinding.ActivityMainBinding;
import com.brandon.quizy.ui.adapter.QuizAttemptAdapter;
import com.brandon.quizy.ui.adapter.TodayQuizAdapter;
import com.brandon.quizy.ui.decoration.HorizontalSpacingDecoration;
import com.brandon.quizy.ui.decoration.LeftPaddingDecoration;
import com.brandon.quizy.ui.decoration.VerticalSpacingDecoration;
import com.brandon.quizy.ui.item.QuizAttemptItem;
import com.brandon.quizy.ui.item.TodayQuizItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();
        initQuizAttemptRecyclerView();
        initTodayQuizRecyclerView();
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
        quizAttemptRecyclerView.addItemDecoration(new LeftPaddingDecoration(this, 30));
    }

    private void initTodayQuizRecyclerView() {
        RecyclerView todayQuizRecyclerView = binding.todayQuizRecyclerView;
        todayQuizRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<TodayQuizItem> todayQuizItems = getTodayQuizzes();
        TodayQuizAdapter todayQuizAdapter = new TodayQuizAdapter(todayQuizItems);

        todayQuizRecyclerView.setAdapter(todayQuizAdapter);
        todayQuizRecyclerView.addItemDecoration(new VerticalSpacingDecoration(50));
    }

    private List<TodayQuizItem> getTodayQuizzes() {
        final List<TodayQuizItem> todayQuizItems = new ArrayList<>();
        todayQuizItems.add(new TodayQuizItem("Programming",3,1,"https://res.cloudinary.com/dohewcyes/image/upload/v1743477503/image/quizy/tgdvx5ubqt58yujtsult.png"));
        todayQuizItems.add(new TodayQuizItem("General Knowledge",2,1,"https://res.cloudinary.com/dohewcyes/image/upload/v1743477503/image/quizy/tgdvx5ubqt58yujtsult.png"));

        return todayQuizItems;
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
