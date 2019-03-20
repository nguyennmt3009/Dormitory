package com.example.skyjar.dormitoryapp.Entities;

import android.media.Image;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ProblemReport implements Serializable {
    private int apartmentId;
    private  String apartmentName;
    private String roomName;
    private String serviceName;
    private String serviceItemName;
    private String description;
    @SerializedName("createdDate")
    private String createDate;
    private int status;
    private String statusName;

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceItemName() {
        return serviceItemName;
    }

    public void setServiceItemName(String serviceItemName) {
        this.serviceItemName = serviceItemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public ProblemReport(int apartmentId, String apartmentName, String roomName, String serviceName, String serviceItemName, String description, String createDate, int status, String statusName) {

        this.apartmentId = apartmentId;
        this.apartmentName = apartmentName;
        this.roomName = roomName;
        this.serviceName = serviceName;
        this.serviceItemName = serviceItemName;
        this.description = description;
        this.createDate = createDate;
        this.status = status;
        this.statusName = statusName;
    }
}
