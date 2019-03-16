package com.example.skyjar.dormitoryapp.Entities;

import java.io.Serializable;

public class ServiceItem implements Serializable {
    private int serviceItemId;
    private String serviceItemName;

    public int getServiceItemId() {
        return serviceItemId;
    }

    public void setServiceItemId(int serviceItemId) {
        this.serviceItemId = serviceItemId;
    }

    public String getServiceItemName() {
        return serviceItemName;
    }

    public void setServiceItemName(String serviceItemName) {
        this.serviceItemName = serviceItemName;
    }

    public ServiceItem(int serviceItemId, String serviceItemName) {

        this.serviceItemId = serviceItemId;
        this.serviceItemName = serviceItemName;
    }
    @Override
    public String toString() {
        return this.serviceItemName;
    }
}
