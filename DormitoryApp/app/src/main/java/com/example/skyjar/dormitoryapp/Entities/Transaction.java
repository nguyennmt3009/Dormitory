package com.example.skyjar.dormitoryapp.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Transaction implements Serializable {
    private int resultCode;
    private String message;
    private String success;
    private String error;
    private List<Trans> data;

    public List<Trans> getData() {
        return data;
    }

    public void setData(List<Trans> data) {
        this.data = data;
    }

    public class Trans implements Serializable {
        private float amount;
        private String date;
        @SerializedName("bill_id")
        private int billId;
        private boolean is_debit;

        public double getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }

        public String getDate() {
            String dateFm = Date(date, "dd/MM/yyyy");
            return dateFm;
        }

        private String Date(String mess, String pattern)
        {
            try
            {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");
                Date tempDate=simpleDateFormat.parse(mess);
                SimpleDateFormat outputDateFormat = new SimpleDateFormat(pattern);
                return outputDateFormat.format(tempDate);
            } catch (Exception e)
            {
            }
            return mess;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getBillId() {
            return billId;
        }

        public void setBillId(int billId) {
            this.billId = billId;
        }

        public boolean isIs_debit() {
            return is_debit;
        }

        public void setIs_debit(boolean is_debit) {
            this.is_debit = is_debit;
        }

        public Trans() {

        }

        public Trans(float amount, String date, int billId, boolean is_debit) {

            this.amount = amount;
            this.date = date;
            this.billId = billId;
            this.is_debit = is_debit;
        }
    }
}
