package com.brandon.quizy.ui.item;

import android.view.View;

public class HeroItem {
    final private String title;
    final private String btnText;
    final private int imageResId;
    final private int layoutResId;

    public HeroItem(String title, String btnText, int imageResId, int layoutResId) {
        this.title = title;
        this.btnText = btnText;
        this.imageResId = imageResId;
        this.layoutResId = layoutResId;
    }

    public String getTitle() {
        return title;
    }

    public String getBtnText() {
        return btnText;
    }

    public int getImageResId() {
        return imageResId;
    }

}
