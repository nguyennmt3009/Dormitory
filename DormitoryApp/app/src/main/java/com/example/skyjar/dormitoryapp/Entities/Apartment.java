package com.example.skyjar.dormitoryapp.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Apartment implements Serializable {
    private int id;
    private String name;
    private List<Room> rooms;
    private  List<Service> services;

    public Apartment(int id, String name, List<Room> rooms, List<Service> services) {
        this.id = id;
        this.name = name;
        this.rooms = rooms;
        this.services = services;
    }

    public Apartment() {
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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static class ReportStatus {
    }
}
