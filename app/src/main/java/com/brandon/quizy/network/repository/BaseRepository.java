package com.brandon.quizy.network.repository;

import androidx.annotation.NonNull;

import com.brandon.quizy.network.ApiService;
import com.brandon.quizy.network.model.response.ErrorResponse;
import com.google.gson.Gson;

import java.util.function.Consumer;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.brandon.quizy.BuildConfig;

public abstract class BaseRepository {
    private final String BASE_URL = BuildConfig.BACKEND_URL;
    protected ApiService apiService;

    public BaseRepository(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public BaseRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .build())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    protected <T> void enqueue(Call<T> call, RepositoryCallback<T> callback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError(getErrorMessage(response));
                }
                callback.onComplete();
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                callback.onError("Network error: " + t.getMessage());
                callback.onComplete();
            }
        });
    }

    public <T> RepositoryCallback<T> makeRepositoryCallback(
            final Consumer<T> onSuccess,
            final Consumer<String> onFailure,
            final Runnable onComplete) {

        return new RepositoryCallback<T>() {
            @Override
            public void onSuccess(T response) {
                onSuccess.accept(response);
            }

            @Override
            public void onError(String error) {
                onFailure.accept(error);
            }

            @Override
            public void onComplete() {
                onComplete.run();
            }
        };
    }


    private String getErrorMessage(Response<?> response) {
        try {
            Gson gson = new Gson();
            ErrorResponse error = gson.fromJson(response.errorBody().charStream(), ErrorResponse.class);
            return error.getMessage();
        } catch (Exception e) {
            return "Unknown error occurred";
        }
    }
}
