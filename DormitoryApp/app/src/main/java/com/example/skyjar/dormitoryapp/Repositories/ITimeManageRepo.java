package com.example.skyjar.dormitoryapp.Repositories;

import android.app.DatePickerDialog;

public interface ITimeManageRepo {
    ITimeManageRepo dialogDatePicker(DatePickerDialog.OnDateSetListener onDateSetListener);

    void showDatePickerDialog();
}
