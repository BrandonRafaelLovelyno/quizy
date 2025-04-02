package com.brandon.quizy.ui.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HorizontalSpacingDecoration extends RecyclerView.ItemDecoration {
    private final int spaceWidth;

    public HorizontalSpacingDecoration(int spaceWidth) {
        this.spaceWidth = spaceWidth;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.right = spaceWidth;
    }
}
