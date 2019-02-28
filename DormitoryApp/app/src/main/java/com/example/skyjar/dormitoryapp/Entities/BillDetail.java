package com.example.skyjar.dormitoryapp.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BillDetail implements Serializable {
    private int id;
    private String month;
    private String room;
    private String apartment;
    private double amount;
    private boolean status;
    private Date createdDate;
    private List<BrandService> serviceList;

    public BillDetail(int id, String month, String room, String apartment,
                      double amount, boolean status, Date createdDate) {
        this.id = id;
        this.month = month;
        this.room = room;
        this.apartment = apartment;
        this.amount = amount;
        this.status = status;
        this.createdDate = createdDate;
    }

    public BillDetail(int id, String month, String room, String apartment, double amount,
                      boolean status, Date createdDate, List<BrandService> serviceList) {
        this.id = id;
        this.month = month;
        this.room = room;
        this.apartment = apartment;
        this.amount = amount;
        this.status = status;
        this.createdDate = createdDate;
        this.serviceList = serviceList;
    }

    public List<BrandService> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<BrandService> serviceList) {
        this.serviceList = serviceList;
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
