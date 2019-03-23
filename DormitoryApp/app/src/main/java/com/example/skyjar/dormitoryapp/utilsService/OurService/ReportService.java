package com.example.skyjar.dormitoryapp.utilsService.OurService;

import com.example.skyjar.dormitoryapp.utilsService.ConfigApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ReportService {
    @GET(ConfigApi.GET_APARTMENT_REPORT + "/{id}")
    Call<ResponseBody> getApartmentReport(@Path("id") int customerId);

    @POST(ConfigApi.GET_APARTMENT_REPORT)
    Call<String> createReport(@Query("roomId") int roomId,
                              @Query("serviceItemId") int serviceItemId,
                              @Query("description") String description);

    @GET(ConfigApi.GET_REPORT_LIST + "/{customerId}")
    Call<ResponseBody> getReportList(@Path("customerId") int customerId);
}
