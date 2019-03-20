package com.example.skyjar.dormitoryapp.Repositories;

import android.content.Context;

import com.example.skyjar.dormitoryapp.Entities.Contract;
import com.example.skyjar.dormitoryapp.utilsService.CallBackData;
import com.example.skyjar.dormitoryapp.utilsService.ClientApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContractRepository {
    public void getContracts(Context context, int customerId, final CallBackData<List<Contract>> callBackData) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> serviceCall = clientApi.contractService().getContract(customerId);
        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response != null && response.body() != null) {
                    if (response.code() == 200) {
                        try {
                            String result = response.body().string();
                            Type type = new TypeToken<List<Contract>>() {
                            }.getType();
                            List<Contract> contractList = new Gson().fromJson(result, type);
                            if (contractList != null) {
                                callBackData.onSuccess(contractList);
                            } else {
                                callBackData.onFail("No data");
                            }
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
}
