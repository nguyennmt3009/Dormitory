package com.example.skyjar.dormitoryapp.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Contract implements Serializable {
    private String ownerName;
    private List<String> listCustomerName;
    private int contractId;
    @SerializedName("createdDate")
    private String createDate;
    private String dueDate;
    private String deposit;
    private String monthlyFee;
    private String roomName;
    private String apartmentName;
    private String brandName;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public List<String> getListCustomerName() {
        return listCustomerName;
    }

    public void setListCustomerName(List<String> listCustomerName) {
        this.listCustomerName = listCustomerName;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(String monthlyFee) {
        this.monthlyFee = monthlyFee;
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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Contract(String ownerName, List<String> listCustomerName, int contractId, String createDate, String dueDate, String deposit, String monthlyFee, String roomName, String apartmentName, String brandName) {

        this.ownerName = ownerName;
        this.listCustomerName = listCustomerName;
        this.contractId = contractId;
        this.createDate = createDate;
        this.dueDate = dueDate;
        this.deposit = deposit;
        this.monthlyFee = monthlyFee;
        this.roomName = roomName;
        this.apartmentName = apartmentName;
        this.brandName = brandName;
    }
}
