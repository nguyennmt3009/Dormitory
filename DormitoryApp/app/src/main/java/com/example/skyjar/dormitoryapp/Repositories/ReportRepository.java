package com.example.skyjar.dormitoryapp.Repositories;

import android.content.Context;

import com.example.skyjar.dormitoryapp.Entities.Apartment;
import com.example.skyjar.dormitoryapp.utilsService.CallBackData;

import java.util.List;

import retrofit2.Call;

public interface ReportRepository {
    void getApartmentReport(Context context,int id, CallBackData<List<Apartment>> callBackData);
    void createReport(int roomId, int serviceItemId, String description, CallBackData<String> callBackData);
}
