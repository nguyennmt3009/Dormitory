package com.example.skyjar.dormitoryapp.utilsService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
}
