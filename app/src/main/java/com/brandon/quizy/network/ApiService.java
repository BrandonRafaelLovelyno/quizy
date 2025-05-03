package com.brandon.quizy.network;

import com.brandon.quizy.network.model.request.LoginRequest;
import com.brandon.quizy.network.model.request.SignupRequest;
import com.brandon.quizy.network.model.response.SignupResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("auth/signup")
    Call<SignupResponse> signup(@Body SignupRequest request);
}
