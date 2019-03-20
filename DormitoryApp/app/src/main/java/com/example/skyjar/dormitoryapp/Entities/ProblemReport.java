package com.example.skyjar.dormitoryapp.Entities;

import android.media.Image;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ProblemReport implements Serializable {
    private  Apartment apartment;
    private String roomName;
    private String serviceName;
    private String serviceItemName;
    private String description;
    @SerializedName("createdDate")
    private String createDate;
    private ReportStatus status;

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
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

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public ProblemReport() {

    }

    public ProblemReport(Apartment apartment, String roomName, String serviceName, String serviceItemName, String description, String createDate, ReportStatus status) {

        this.apartment = apartment;
        this.roomName = roomName;
        this.serviceName = serviceName;
        this.serviceItemName = serviceItemName;
        this.description = description;
        this.createDate = createDate;
        this.status = status;
    }
}
