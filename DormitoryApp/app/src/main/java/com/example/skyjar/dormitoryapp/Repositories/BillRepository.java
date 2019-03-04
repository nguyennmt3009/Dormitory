package com.example.skyjar.dormitoryapp.Repositories;

import android.content.Context;

import com.example.skyjar.dormitoryapp.Entities.BillDetail;
import com.example.skyjar.dormitoryapp.utilsService.CallBackData;

import java.util.List;

public interface BillRepository {
    void getBills(Context context, CallBackData<List<BillDetail>> callBackData);

    void getBillDetail(Context context, int id, CallBackData<BillDetail> callBackData);
}
