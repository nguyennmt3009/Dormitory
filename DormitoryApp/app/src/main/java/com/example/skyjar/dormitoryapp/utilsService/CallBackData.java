package com.example.skyjar.dormitoryapp.utilsService;

public interface CallBackData<T> {
    void onSuccess(T t);

    void onFail(String msg);
}
