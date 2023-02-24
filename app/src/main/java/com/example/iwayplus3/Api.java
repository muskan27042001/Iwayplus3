package com.example.iwayplus3;

import com.example.iwayplus3.ModelResponse.JWTToken;
import com.example.iwayplus3.ModelResponse.LoginResponse;
import com.example.iwayplus3.ModelResponse.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("/user/signup")
    Call<RegisterResponse> register(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/user/signin")
    Call<JWTToken> userlogin(
            @Field("username") String username,
            @Field("password") String password
    );

//    @GET("signin")
//    Call<String> getUser(@Header("alg") String alg);

/*@FormUrlEncoded
    @POST("signin")
    Call<LoginResponse> login(
            @Field("_id") String _id,
            @Field("email") String email
    );*/


}
