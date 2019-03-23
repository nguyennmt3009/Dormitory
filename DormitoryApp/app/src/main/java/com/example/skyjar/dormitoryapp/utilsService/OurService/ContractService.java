package com.example.skyjar.dormitoryapp.utilsService.OurService;

import com.example.skyjar.dormitoryapp.utilsService.ConfigApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ContractService {
    @GET(ConfigApi.GET_CONTRACTS + "/{customerId}")
    Call<ResponseBody> getContract(@Path("customerId") int customerId);
}
