package com.brandon.quizy.utils;

import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

public class InputValidator {
    public static boolean isEmailValid(String email) {
        boolean isValid = true;

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isValid = false;
        }

        return isValid;
    }

    public static boolean isEmailValid(String email, Context context){
        boolean isValid = true;

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(context, "Invalid email format.", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        return isValid;
    }

    public static boolean isPasswordValid(String password) {
        return password != null && password.length() >= 6;
    }

    public static boolean isPasswordValid(String password, Context context) {
        boolean isValid = true;

        if (password == null || password.length() < 6) {
            isValid = false;
        }

        return isValid;
    }

    public static boolean isNameValid(String name) {
        return name != null && name.trim().matches("^[A-Za-z\\s]+$");
    }

    public static boolean isNameValid(String name, Context context) {
        boolean isValid = true;

        if (name == null || !name.trim().matches("^[A-Za-z\\s]+$")) {
            Toast.makeText(context, "Name must contain only letters and spaces.", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        return isValid;
    }

}
