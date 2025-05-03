package com.brandon.quizy.network.repository;

public interface RepositoryCallback<T> {
    void onSuccess(T response);
    void onError(String error);
    void onComplete();
}