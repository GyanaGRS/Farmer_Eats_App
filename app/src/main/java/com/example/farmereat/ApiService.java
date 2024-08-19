package com.example.farmereat;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("register")  // Endpoint for registration
    Call<RegisterResponseModel> registerUser(@Body RegisterRequestModel registerRequestModel);

    @POST("login")     // Endpoint for login
    Call<LoginResponseModel> loginUser(@Body LoginRequestModel loginRequestModel);
}
