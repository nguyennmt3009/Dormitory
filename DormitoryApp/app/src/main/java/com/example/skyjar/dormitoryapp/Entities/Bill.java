package com.example.skyjar.dormitoryapp.Entities;

import java.io.Serializable;
import java.util.List;

public class Bill implements Serializable {
    private int id;
    private String month;
    private String roomName;
    private String apartmentName;
    private double amount;
    private String status;
    private String createdDate;
    private List<BillDetail> billDetails;

    public Bill(int id, String month, String roomName, String apartmentName,
                double amount, String status, String createdDate) {
        this.id = id;
        this.month = month;
        this.roomName = roomName;
        this.apartmentName = apartmentName;
        this.amount = amount;
        this.status = status;
        this.createdDate = createdDate;
    }

    public Bill(int id, String month, String roomName, String apartmentName, double amount,
                String status, String createdDate, List<BillDetail> billDetails) {
        this.id = id;
        this.month = month;
        this.roomName = roomName;
        this.apartmentName = apartmentName;
        this.amount = amount;
        this.status = status;
        this.createdDate = createdDate;
        this.billDetails = billDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BillDetail> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<BillDetail> billDetails) {
        this.billDetails = billDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
