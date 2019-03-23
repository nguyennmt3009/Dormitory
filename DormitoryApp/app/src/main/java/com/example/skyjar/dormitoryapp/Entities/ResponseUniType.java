package com.example.skyjar.dormitoryapp.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseUniType implements Serializable {
    @SerializedName("result-code")
    private int resultCode;
    private String message;
    private String success;
    private String error;
    private Account data;

    public Account getData() {
        return data;
    }

    public void setData(Account data) {
        this.data = data;
    }

    public class Account {
        @SerializedName("customer_id")
        private String customerId;
        @SerializedName("list_account")
        private AccountItem accountItem;

        public AccountItem getAccountItem() {
            return accountItem;
        }

        public void setAccountItem(AccountItem accountItem) {
            this.accountItem = accountItem;
        }

        public class AccountItem {
            @SerializedName("customer_id")
            private String customerId;
            @SerializedName("amount_balance")
            private int amount;
            private String status;

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }
        }
    }
}


