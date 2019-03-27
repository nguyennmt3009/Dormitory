package com.example.skyjar.dormitoryapp.utilsService.OurService;

import com.example.skyjar.dormitoryapp.utilsService.ConfigApi;

import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TransactionService {
    @GET(ConfigApi.GET_TRANSACTION_LIST )
    Call<ResponseBody> getTransactionList(@Query("customer_id") int customerId, @Query("from") String fromDate, @Query("to") String toDate);
}
