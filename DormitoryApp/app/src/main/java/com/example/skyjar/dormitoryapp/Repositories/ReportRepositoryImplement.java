package com.example.skyjar.dormitoryapp.Repositories;

import android.content.Context;

import com.example.skyjar.dormitoryapp.Entities.Apartment;
import com.example.skyjar.dormitoryapp.Entities.Room;
import com.example.skyjar.dormitoryapp.Entities.Service;
import com.example.skyjar.dormitoryapp.Entities.ServiceItem;
import com.example.skyjar.dormitoryapp.utilsService.CallBackData;
import com.example.skyjar.dormitoryapp.utilsService.ClientApi;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class ReportRepositoryImplement implements ReportRepository {

    @Override
    public void getApartmentReport(Context context, int id, final CallBackData<List<Apartment>> callBackData) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> serviceCall = clientApi.reportService().getApartmentReport(id);
        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response != null && response.body() != null) {
                    if (response.code() == 200) {
                        try {
                            String result = response.body().string();
                            Type type = new TypeToken<List<Apartment>>() {
                            }.getType();
                            List<Apartment> apartmentList = new Gson().fromJson(result, type);
                            callBackData.onSuccess(apartmentList);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    callBackData.onFail("No data");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void createReport(int roomId, int serviceItemId, String description, final CallBackData<String> callBackData) {
        ClientApi clientApi = new ClientApi();
        Call<String> serviceCall = clientApi.reportService().createReport(roomId, serviceItemId, description);
        serviceCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                    if (response.code() == 200) {
                            callBackData.onSuccess("success here");
                        }

                else {
                    callBackData.onFail("No submited");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

}
