package com.pluralsight.dealership.DAO;
import com.pluralsight.dealership.model.SalesContract;
import com.pluralsight.dealership.model.Vehicle;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ResourceBundle;

@Component
public class SalesContractDAO {
    private DataSource datasource;
    public SalesContractDAO(DataSource datasource) {
        this.datasource = datasource;
    }

    public ArrayList<SalesContract> findSalesContract(int id) {
        String query = """
                SELECT * FROM sales_contract
                JOIN vehicle ON sales_contracts.vin = vehicles.vin
                WHERE contracts_id = ?;
                """;
        ArrayList<SalesContract> salesContracts = new ArrayList<>();
        try (Connection connection = datasource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            {
                while (rs.next()) {
                    String date = rs.getString("sale_date");
                    String customerName = rs.getString("customer_name");
                    String customerEmail = rs.getString("customer_email");
                    boolean isFinanced = rs.getBoolean("financed");
                    String vin = rs.getString("vin");
                    int year = rs.getInt("year");
                    double price = rs.getDouble("price");
                    String make = rs.getString("make");
                    String model = rs.getString("model");
                    String color = rs.getString("color");
                    int odometer = rs.getInt("odometer");
                    String vehicle_type = rs.getString("vehicle_type");

                    Vehicle vehicleSold = new Vehicle(vin, year, price, make, model, color, odometer, vehicle_type);
                    SalesContract salesContract = new SalesContract(date, customerName, customerEmail, vehicleSold, isFinanced);
                    salesContracts.add(salesContract);


                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return salesContracts;
    }
    // Add a Sales Contract
    public void addSalesContract(SalesContract salesContract) {
        String query = """ 
                INSERT INTO sales_contracts (
                vin, sale_date, sale_price, customer_name
                sales_tax_rate, recording_fee)
                VALUES (?, ?, ?, ?, ?, ?);
                        """;
        try (Connection connection = datasource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, salesContract.getVin());stmt.setString(2, salesContract.getSaleDate());
            stmt.setDouble(3, salesContract.getSalePrice());
            stmt.setString(4, salesContract.getCustomerName());
            stmt.setDouble(5, salesContract.getSalesTaxAmount());
            stmt.setDouble(6, salesContract.getRecordingFee());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            }

    }
}
