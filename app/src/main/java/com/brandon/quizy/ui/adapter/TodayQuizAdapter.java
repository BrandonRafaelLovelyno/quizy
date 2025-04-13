package com.brandon.quizy.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.brandon.quizy.R;
import com.brandon.quizy.ui.item.TodayQuizItem;
import com.bumptech.glide.Glide;

import java.util.List;

public class TodayQuizAdapter extends RecyclerView.Adapter<TodayQuizAdapter.ItemViewHolder> {
    final private List<TodayQuizItem> todayQuizzes;

    public TodayQuizAdapter(List<TodayQuizItem> todayQuizzes) {
        this.todayQuizzes = todayQuizzes;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_today_quiz, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        TodayQuizItem todayQuizItem = todayQuizzes.get(position);
        holder.bind(todayQuizItem);
    }

    @Override
    public int getItemCount() {
        return todayQuizzes.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        final private TextView categoryView;
        final private ImageView imgView;
        final private TextView progressTextView;
        final private View progressBarForegroundView;
        final private View progressBarContainerView;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryView = itemView.findViewById(R.id.quizCategory);
            imgView = itemView.findViewById(R.id.image);
            progressTextView = itemView.findViewById(R.id.quizProgressText);
            progressBarForegroundView = itemView.findViewById(R.id.progressBarForeground);
            progressBarContainerView = itemView.findViewById(R.id.progressBarContainer);
        }

        public void bind(TodayQuizItem todayQuizItem) {
            categoryView.setText(todayQuizItem.getQuizCategory());
            bindImageUrl(todayQuizItem.getImageUrl());
            bindProgressText(todayQuizItem.getDoneQuiz(), todayQuizItem.getTotalQuiz());

            progressBarContainerView.post(()->{
                bindProgressBar(todayQuizItem.getDoneQuiz(), todayQuizItem.getTotalQuiz());
            });
        }

        public void bindImageUrl(String imageUrl) {
            Glide.with(imgView.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.img_create_quiz)
                    .into(imgView);
        }

        public void bindProgressBar(int doneQuiz, int totalQuiz) {
            float percentage = (float) doneQuiz / totalQuiz;

            int totalWidth = progressBarContainerView.getWidth();
            int foregroundWidth = (int) (totalWidth * percentage);

            ViewGroup.LayoutParams params = progressBarForegroundView.getLayoutParams();
            params.width = foregroundWidth;
            progressBarForegroundView.setLayoutParams(params);
        }

        public void bindProgressText(int doneQuiz, int totalQuiz){
            String progressText = doneQuiz + "/" + totalQuiz + " quizzes taken";
            progressTextView.setText(progressText);
        }

    }
}
