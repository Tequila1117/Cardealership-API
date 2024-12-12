package com.pluralsight.dealership.model;

public class Contract {

    //Declare instance variables
    private String date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;
    private double totalPrice;
    private double monthlyPayment;

    //Constructor to initialize contract fields

    public Contract(String date, String customerName, String customerEmail, Vehicle vehicle ) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;

        this.vehicleSold = vehicle;
    }

    //Getters for contract fields

    public String getDate() { return date; }
    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public Vehicle getVehicleSold() { return vehicleSold; }
    public double getTotalPrice() { return totalPrice; }
    public double getMonthlyPayment() { return monthlyPayment; }



}
