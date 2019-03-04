package com.example.skyjar.dormitoryapp.utilsService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BillService {

    @GET(ConfigApi.GET_BILL_DETAIL)
    Call<ResponseBody> getBills();
    @GET(ConfigApi.GET_BILL_DETAIL + "/{id}")
    Call<ResponseBody> getBillDetail(@Path("id") int billId);
}
