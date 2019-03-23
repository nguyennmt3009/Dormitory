package com.example.skyjar.dormitoryapp.utilsService.OurService;

import com.example.skyjar.dormitoryapp.utilsService.ConfigApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TransactionService {
    @GET(ConfigApi.GET_CUSTOMER_ACCOUNT)
    Call<ResponseBody> getCustomerAccount(@Query("customer_id") int customerId);
}
