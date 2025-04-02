package com.brandon.quizy.ui.decoration;

import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LeftPaddingDecoration extends RecyclerView.ItemDecoration {
    private final int leftPaddingPx;

    public LeftPaddingDecoration(Context context, int leftPaddingDp) {
        // Convert dp to pixels
        this.leftPaddingPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, leftPaddingDp, context.getResources().getDisplayMetrics());
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position == 0) { // Apply padding only to the first item
            outRect.left = leftPaddingPx;
        }
    }
}

