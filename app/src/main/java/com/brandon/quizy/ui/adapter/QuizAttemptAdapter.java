package com.brandon.quizy.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brandon.quizy.R;
import com.brandon.quizy.ui.item.QuizAttemptItem;

import com.bumptech.glide.Glide;

import java.util.List;

public class QuizAttemptAdapter extends RecyclerView.Adapter<QuizAttemptAdapter.ItemViewHolder> {
    final private List<QuizAttemptItem> quizAttemptItems;

    public QuizAttemptAdapter(List<QuizAttemptItem> quizAttemptItems) {
        this.quizAttemptItems = quizAttemptItems;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_quiz_attempt, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        QuizAttemptItem quizAttempItem = quizAttemptItems.get(position);
        holder.bind(quizAttempItem);
    }

    @Override
    public int getItemCount() {
        return quizAttemptItems.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        final private TextView quizNameView;
        final private ImageView imgView;
        final private TextView rankView;
        final private TextView pointView;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            quizNameView = itemView.findViewById(R.id.quizTitle);
            imgView = itemView.findViewById(R.id.image);
            rankView = itemView.findViewById(R.id.quizRank);
            pointView = itemView.findViewById(R.id.quizPoint);
        }

        public void bind(QuizAttemptItem quizAttemptItem) {
            quizNameView.setText(quizAttemptItem.getQuizName());
            bindImageUrl(quizAttemptItem.getImageUrl());
            bindRank(quizAttemptItem.getRank(), quizAttemptItem.getParticipant());
            bindPoint(quizAttemptItem.getPoint());
        }

        public void bindRank(int rank, int participant) {
            String rankText = "Rank " + rank + "/" + participant;
            rankView.setText(rankText);
        }

        public void bindPoint(int point) {
            String pointText = point + " Point";
            pointView.setText(pointText);
        }

        public void bindImageUrl(String imageUrl) {
            Glide.with(imgView.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.img_create_quiz)
                    .into(imgView);
        }
    }
}
