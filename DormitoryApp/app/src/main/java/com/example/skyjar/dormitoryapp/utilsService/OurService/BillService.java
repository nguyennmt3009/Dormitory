package com.example.skyjar.dormitoryapp.utilsService.OurService;

import com.example.skyjar.dormitoryapp.utilsService.ConfigApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BillService {

    @GET(ConfigApi.GET_BILL_DETAIL + "/{order}/{customerId}")
    Call<ResponseBody> getBills(@Path("order") String order, @Path("customerId") int customerId);


    @POST(ConfigApi.PAYMENT)
    Call<ResponseBody> payment(@Query("customerId") int customerId, @Query("billId") int billId);
}
