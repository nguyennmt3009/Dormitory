package com.example.skyjar.dormitoryapp.Entities;

import java.io.Serializable;
import java.util.List;

public class Service implements Serializable {
    private int id;
    private String name;
    private List<ServiceItem> serviceItems;

    public Service(int id, String name, List<ServiceItem> serviceItems) {
        this.id = id;
        this.name = name;
        this.serviceItems = serviceItems;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ServiceItem> getServiceItems() {
        return serviceItems;
    }

    public void setServiceItemList(List<ServiceItem> serviceItems) {
        this.serviceItems = serviceItems;
    }
    @Override
    public String toString() {
        return this.name;
    }
}
