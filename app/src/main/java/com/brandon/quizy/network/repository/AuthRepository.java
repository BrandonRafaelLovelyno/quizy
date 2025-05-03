package com.brandon.quizy.network.repository;

import com.brandon.quizy.network.model.request.SignupRequest;
import com.brandon.quizy.network.model.response.SignupResponse;

import retrofit2.Call;

public class AuthRepository extends BaseRepository {

    public AuthRepository() {
        super();
    }

    public void signup(String email, String username, String password, final RepositoryCallback<SignupResponse> callback) {
        SignupRequest request = new SignupRequest(username, email, password);
        Call<SignupResponse> call = apiService.signup(request);
        enqueue(call, callback);
    }
}
