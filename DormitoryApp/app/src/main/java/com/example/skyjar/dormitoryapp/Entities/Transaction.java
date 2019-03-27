package com.example.skyjar.dormitoryapp.Entities;

import java.io.Serializable;
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

    public class Trans {
        private double amount;
        private String date;
        private int billId;
        private boolean is_debit;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getDate() {
            return date;
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

        public Trans(double amount, String date, int billId, boolean is_debit) {

            this.amount = amount;
            this.date = date;
            this.billId = billId;
            this.is_debit = is_debit;
        }
    }
}
