package com.example.skyjar.dormitoryapp.utilsService.OurService;

import com.example.skyjar.dormitoryapp.utilsService.ConfigApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {

    @FormUrlEncoded
    @POST(ConfigApi.LOGIN)
    Call<ResponseBody> login(@Field("grant_type") String grant_type, @Field("username") String username,
                             @Field("password") String password);


    @GET(ConfigApi.GET_USER_DETAIL)
    Call<ResponseBody> getUserDetail(@Header("Authorization") String auth);

}
