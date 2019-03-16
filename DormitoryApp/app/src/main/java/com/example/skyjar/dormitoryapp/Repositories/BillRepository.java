package com.example.skyjar.dormitoryapp.Repositories;

import android.content.Context;

import com.example.skyjar.dormitoryapp.Entities.Bill;
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

public class BillRepository  {

    public void paymentBill(int customerId, int billId, final CallBackData<String> callBackData) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> serviceCall= clientApi.billService().payment(customerId, billId);

        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response!=null && response.body() != null){
                    if(response.code()==200){
                        callBackData.onSuccess("Success");
                    } else {
                        callBackData.onFail("Bad request");
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

    public void getBills(Context context, String order, int customerId, final CallBackData<List<Bill>> callBackData) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> serviceCall= clientApi.billService().getBills(order, customerId);

        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response!=null && response.body() != null){
                    if(response.code()==200){
                        try {
                            String result = response.body().string();
                            Type type= new TypeToken<List<Bill>>(){}.getType();

                            List<Bill> billList = new Gson().fromJson(result,type);

                            if(billList != null){
                                callBackData.onSuccess(billList);
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