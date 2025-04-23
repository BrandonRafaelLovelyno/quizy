package com.brandon.quizy.utils;

import android.util.Patterns;

public class InputValidator {
    public static boolean isEmailValid(String email) {
        boolean isValid = true;

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isValid = false;
        }

        return isValid;
    }

    public static boolean isPasswordValid(String password) {
        return password != null && password.length() >= 6;
    }

    public static boolean isNameValid(String name) {
        return name != null && name.trim().matches("^[A-Za-z\\s]+$");
    }

    public static boolean isQuizTitleValid(String title) {
        return title != null && title.trim().matches("^[A-Za-z0-9\\s]+$") && title.length() <= 20 && title.length() >= 4;
    }
}
