package com.example.skyjar.dormitoryapp.Repositories;

import com.example.skyjar.dormitoryapp.Entities.LoginEntites.LoginResponse;
import com.example.skyjar.dormitoryapp.Entities.LoginEntites.UserLogin;
import com.example.skyjar.dormitoryapp.Entities.User;
import com.example.skyjar.dormitoryapp.utilsService.CallBackData;
import com.example.skyjar.dormitoryapp.utilsService.ClientApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    public void getUserDetail(String auth, final CallBackData<User> callBackData) {
        ClientApi clientApi = new ClientApi();

        Call<ResponseBody> serviceCall= clientApi.userService().getUserDetail("Bearer " + auth);

        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response!=null && response.body() != null){
                    if(response.code()==200){
                        try {
                            String result = response.body().string();
                            Type type= new TypeToken<User>(){}.getType();

                            User user = new Gson().fromJson(result,type);

                            if(user != null){
                                callBackData.onSuccess(user);
                            }else {
                                callBackData.onFail("No data!");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }else {
                    callBackData.onFail("No data");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    public void login(String username, String password, final CallBackData<LoginResponse> callBackData) {
        ClientApi clientApi = new ClientApi();

        Call<ResponseBody> serviceCall= clientApi.userService().login("password", username, password);

        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response!=null && response.body() != null){
                    if(response.code()==200){
                        try {
                            String result = response.body().string();
                            Type type= new TypeToken<LoginResponse>(){}.getType();

                            LoginResponse loginEntity = new Gson().fromJson(result,type);

                            if(loginEntity != null){
                                callBackData.onSuccess(loginEntity);
                            }else {
                                callBackData.onFail("No data!");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }else {
                    callBackData.onFail("No data");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
