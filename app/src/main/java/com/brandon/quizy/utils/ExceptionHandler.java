package com.brandon.quizy.utils;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class ExceptionHandler {

    static public String getFriendlyErrorMessage(Exception e) {
        if (e instanceof FirebaseAuthInvalidUserException || e instanceof FirebaseAuthInvalidCredentialsException) {
            return "Invalid email or password.";
        } else if (e instanceof FirebaseAuthWeakPasswordException) {
            return "Your password is too weak.";
        } else if (e instanceof FirebaseAuthUserCollisionException) {
            return "This email is already registered.";
        } else if (e instanceof FirebaseAuthException) {
            return "Authentication error: " + e.getMessage(); // fallback for other Firebase-specific issues
        } else {
            return "Login failed. Please try again.";
        }
    }

}
