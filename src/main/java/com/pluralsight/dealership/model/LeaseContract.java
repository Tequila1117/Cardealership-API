package com.pluralsight.dealership.model;

public class LeaseContract extends Contract {
    private static final double leaseFeeRate = 0.07; // Lease fee rate (7% of original price)
    private final double expectedEndingValue; // Expected ending value at the end of the lease

    // Constructor to initialize a lease contract
    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);
        this.expectedEndingValue = vehicle.getPrice() * 0.5; // Calculate expected ending value (50% of original price)
    }

    // Calculate total price including lease fee
    @Override
    public double getTotalPrice() {
        return calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        double leaseFee = getVehicleSold().getPrice() * leaseFeeRate; // Calculate lease fee
        return getVehicleSold().getPrice() + leaseFee; // Total price = vehicle price plus lease fee
    }

    // Calculate monthly payment based on lease conditions
    @Override
    public double getMonthlyPayment() {
        return calculateMonthlyPayment(); // Call the calculation method
    }

    private double calculateMonthlyPayment() {
        double totalPrice = calculateTotalPrice();
        double interestRate = 0.04; // Lease interest rate (4% for all leases)
        int months = 36; // Lease term (36 months)

        // Formula for monthly payment calculation
        return (totalPrice * interestRate / months);
    }

    // Get expected ending value
    public double getExpectedEndingValue() {
        return expectedEndingValue;
        // Keep this if it's used elsewhere
    }
}