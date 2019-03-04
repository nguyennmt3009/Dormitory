package com.example.skyjar.dormitoryapp.Repositories;

import android.content.Context;

import com.example.skyjar.dormitoryapp.Entities.BillDetail;
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

public class BillRepositoryImplement implements BillRepository {


    @Override
    public void getBills(Context context, final CallBackData<List<BillDetail>> callBackData) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> serviceCall= clientApi.billService().getBills();

        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response!=null && response.body() != null){
                    if(response.code()==200){
                        try {
                            String result = response.body().string();
                            Type type= new TypeToken<List<BillDetail>>(){}.getType();

                            List<BillDetail> billList = new Gson().fromJson(result,type);

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

    @Override
    public void getBillDetail(Context context, int id, final CallBackData<BillDetail> callBackData) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> serviceCall= clientApi.billService().getBillDetail(id);

        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response!=null && response.body() != null){
                    if(response.code()==200){
                        try {
                            String result = response.body().string();
                            Type type= new TypeToken<BillDetail>(){}.getType();

                            BillDetail billDetail = new Gson().fromJson(result,type);

                            if(billDetail!=null){
                                callBackData.onSuccess(billDetail);
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