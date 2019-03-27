package com.example.skyjar.dormitoryapp.Repositories;

import android.app.DatePickerDialog;
import android.content.Context;

import java.util.Calendar;

public class TimeManageRepo implements ITimeManageRepo {
    private static final String DATE_PICKER = "mDatePicker";
    private static final String DAY_FIELD = "day";
    private static final String ID = "id";
    private static final String ANDROID = "android";
    private Context mContext;
    private DatePickerDialog mDatePickerDialog;
    private Calendar mCalendar;


    public TimeManageRepo(Context mContext) {
        this.mContext = mContext;
        this.mCalendar = Calendar.getInstance();
    }

    @Override
    public ITimeManageRepo dialogDatePicker(DatePickerDialog.OnDateSetListener onDateSetListener) {
        mDatePickerDialog = new DatePickerDialog(mContext, onDateSetListener, mCalendar.get(Calendar.YEAR),
                        mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        return this;
    }

    @Override
    public void showDatePickerDialog() {
        if (mDatePickerDialog == null) {
            return;
        }
        mDatePickerDialog.show();
    }
}
