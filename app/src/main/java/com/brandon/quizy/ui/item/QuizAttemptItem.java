package com.brandon.quizy.ui.item;

public class QuizAttemptItem {
    final private String quizName;
    final private String imageUrl;
    final private int point;
    final private int rank;
    final private int participant;

    public QuizAttemptItem(String quizName, String imageUrl, int point, int rank, int participant) {
        this.quizName = quizName;
        this.imageUrl = imageUrl;
        this.point = point;
        this.rank = rank;
        this.participant = participant;
    }

    public String getQuizName() {
        return quizName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getPoint() {
        return point;
    }

    public int getRank() {
        return rank;
    }

    public int getParticipant() {
        return participant;
    }
}
