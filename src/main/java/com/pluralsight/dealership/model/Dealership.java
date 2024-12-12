package com.pluralsight.dealership.model;


import java.util.List;

public class Dealership {
    private int Id;
    private String name;
    private String address;
    private String phone;

    // Constructor
    public Dealership(int Id, String name, String address, String phone) {
        this.Id = Id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    // Getters and Setters
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public void removeVehicle(Vehicle vehicleToRemove) {
    }
}
