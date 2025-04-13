package com.brandon.quizy.ui.item;

public class TodayQuizItem {
    final private String quizCategory;
    final private int totalQuiz;
    final private int doneQuiz;
    final private String imageUrl;
    public TodayQuizItem(String quizCategory, int totalQuiz, int doneQuiz, String imageUrl) {
        this.quizCategory = quizCategory;
        this.totalQuiz = totalQuiz;
        this.doneQuiz = doneQuiz;
        this.imageUrl = imageUrl;
    }

    public String getQuizCategory() {
        return quizCategory;
    }

    public int getTotalQuiz() {
        return totalQuiz;
    }

    public int getDoneQuiz() {
        return doneQuiz;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
