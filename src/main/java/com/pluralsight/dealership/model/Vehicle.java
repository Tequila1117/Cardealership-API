package com.pluralsight.dealership.model;

public class Vehicle {
    private String vin;
    private int year;
    private double price;
    private String make;
    private String model;
    private String color;
    private boolean sold;
    private String type; // ex. truck, suv, motorcycle

    // Constructor
    public Vehicle(String vin, int year, double price, String make, String model, String color, boolean sold, String type) {
        this.vin = vin;
        this.year = year;
        this.price = price;
        this.make = make;
        this.model = model;
        this.color = color;
        this.sold = sold;
        this.type = type;
    }

    public Vehicle(String vin, String model) {

    }

    public Vehicle(String vin, int year, String make, String model, String type, String color, int odometer, double price, String type1) {
    }


    // Getters and Setters
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Object getType() {
    return type;

    }

}