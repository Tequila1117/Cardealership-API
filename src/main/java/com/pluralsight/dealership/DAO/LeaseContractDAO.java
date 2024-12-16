package com.pluralsight.dealership.DAO;

import com.pluralsight.dealership.model.LeaseContract;
import com.pluralsight.dealership.model.SalesContract;
import com.pluralsight.dealership.model.Vehicle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeaseContractDAO {
    private DataSource datasource;

    public LeaseContractDAO(DataSource datasource) {
        this.datasource = datasource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {
        String query = """
                 INSERT INTO lease_contracts
                 (date, total_price, customer_name, customer_email, monthly_payment)
                 VALUES (?, ?, ?, ?, ?); 
                             
                """;
        try (Connection connection = datasource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, leaseContract.getDate());
            stmt.setDouble(2, leaseContract.getTotalPrice());
            stmt.setString(3, leaseContract.getCustomerName());
            stmt.setString(4, leaseContract.getCustomerEmail());
            stmt.setDouble(5, leaseContract.getMonthlyPayment());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    // Find Lease Contract by ID
    public List<LeaseContract> findLeaseContract(int id) {
        String query = """
                SELECT * FROM lease_contracts
                JOIN vehicles ON vehicles.vin = lease_contracts.vin
                ORDER BY date;
                """;
        List<LeaseContract> leaseContracts = new ArrayList<>();
        try (Connection connection = datasource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            {
                while (rs.next()) {
                    String date = rs.getString("sale_date");
                    String customerName = rs.getString("customer_name");
                    String customerEmail = rs.getString("customer_email");
                    String vin = rs.getString("vin");
                    int year = rs.getInt("year");
                    double price = rs.getDouble("price");
                    String make = rs.getString("make");
                    String model = rs.getString("model");
                    String color = rs.getString("color");
                    int odometer = rs.getInt("odometer");
                    String vehicle_type = rs.getString("vehicle_type");
                    Vehicle vehicleSold = new Vehicle(vin, year, price, make, model, color, odometer, vehicle_type);
                    LeaseContract leaseContract = new LeaseContract(date, customerName, customerEmail, vehicleSold);
                    leaseContracts.add(leaseContract);

                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return leaseContracts;
    }
}



