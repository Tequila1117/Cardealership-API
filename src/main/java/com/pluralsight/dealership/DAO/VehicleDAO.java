package com.pluralsight.dealership.DAO;

import com.pluralsight.dealership.model.Vehicle;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class VehicleDAO {

    private DataSource datasource;

    //Constructor
    public VehicleDAO(DataSource datsource) {
        this.datasource = datsource;
    }

    // Create a new vehicle
    public void createVehicle(Vehicle vehicle) {
        String query = "INSERT INTO vehicles (vin, year, price, make, model, color, sold, vehicle_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = datasource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, vehicle.getVin());
            stmt.setInt(2, vehicle.getYear());
            stmt.setDouble(3, vehicle.getPrice());
            stmt.setString(4, vehicle.getMake());
            stmt.setString(5, vehicle.getModel());
            stmt.setString(6, vehicle.getColor());
            stmt.setBoolean(7, vehicle.isSold());

            stmt.executeUpdate();
            System.out.println("Vehicle created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all vehicles
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles";

        try (Connection connection = datasource.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Vehicle vehicle = new Vehicle(
                        rs.getString("vin"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getString("color"),
                        rs.getInt("odometer"),
                        rs.getBoolean("sold"),
                        rs.getString("vehicle_type")
                );
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    // Retrieve a vehicle by VIN
    public Vehicle getVehicleByVin(String vin) {

        String query = "SELECT * FROM vehicles WHERE vin = ?";

        // declare vehicle variable and assign as null.
        Vehicle vehicle = null;
        try (Connection connection = datasource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, vin);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                vehicle = new Vehicle(
                        rs.getString("vin"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
                        rs.getString("make"),
                        rs.getString("model"),

                        rs.getString("color"),
                        rs.getInt("odometer"),
                        rs.getBoolean("sold"),
                        rs.getString("vehicle_type"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicle;
    }

    // Update a vehicle
    public void updateVehicle(Vehicle vehicle) {
        String query = "UPDATE vehicles SET year = ?, price = ?, make = ?, model = ?, color = ?, sold = ? WHERE vin = ?";

        try (Connection connection = datasource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setInt(1, vehicle.getYear());
            stmt.setDouble(2, vehicle.getPrice());
            stmt.setString(3, vehicle.getMake());
            stmt.setString(4, vehicle.getModel());
            stmt.setString(5, vehicle.getColor());
            stmt.setBoolean(6, vehicle.isSold());
            stmt.setString(7, vehicle.getVin());
            stmt.setString(8, vehicle.getType());

            stmt.executeUpdate();
            System.out.println("Vehicle updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a vehicle by VIN
    public void deleteVehicle(String vin) {
        String query = "DELETE FROM vehicles WHERE vin = ?";

        try (Connection connection = datasource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, vin);
            stmt.executeUpdate();
            System.out.println("Vehicle deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Retrieve vehicles by price range (min/max)
    public List<Vehicle> findAllVehicleByPriceRange(double minPrice, double maxPrice) {
        List<Vehicle> vehicles = new ArrayList<>();
        int odometer, year;
        String vin, make, model, vehicleType, color;
        double price;
        boolean sold;
        String query = """ 
                SELECT * FROM vehicles
                WHERE price between ? and ?""";

        try (Connection connection = datasource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setDouble(1, minPrice);
            stmt.setDouble(2, maxPrice);
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Vehicle vehicle = new Vehicle(
                        rs.getString("vin"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
                        rs.getString("make"),
                        rs.getString("model"),

                        rs.getString("color"),
                        rs.getInt("odometer"),
                        rs.getBoolean("sold"),
                        rs.getString("vehicle_type")
                );
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }
    // Get vehicle based on dealership


    public List<Vehicle> findVehiclesByDealership(int id) {
        List<Vehicle> vehicles = new ArrayList<>();
        int odometer, year;
        String vin, make, model, vehicleType, color;
        double price;
        boolean sold;
        String query = """
                SELECT vin, year, make, model, color, vehicle_type, sold
                FROM inventory
                JOIN vehicles ON inventory.vin = vehicles.vin
                WHERE dealership_id =?;
                """;

        try (Connection connection = datasource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                vin = rs.getString("vin");
                year = rs.getInt("year");
                make = rs.getString("make");
                model = rs.getString("model");
                price = rs.getDouble("price");
                color = rs.getString("color");
                odometer = rs.getInt("odometer");
                vehicleType = rs.getString("vehicle_type");
                sold = rs.getBoolean("sold");

                vehicles.add(new Vehicle(vin, year, price, make, model, color, odometer, sold, vehicleType));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    //Retrieve a vehicle by make/model
    public List<Vehicle> findVehiclesByMakeModel(String carMake, String carModel) {
        List<Vehicle> vehicles = new ArrayList<>();
        String vin, make, model, color, vehicleType;
        double price;
        int year, odometer;
        boolean sold;
        String query = """
                SELECT vin, year, make, model, color, vehicle_type, sold
                FROM vehicles
                WHERE make = ? AND model = ?
                """;
        try (Connection connection = datasource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, carMake);
            stmt.setString(2, carModel);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                vin = rs.getString("vin");
                year = rs.getInt("year");
                make = rs.getString("make");
                model = rs.getString("model");
                price = rs.getDouble("price");
                color = rs.getString("color");
                odometer = rs.getInt("odometer");
                vehicleType = rs.getString("vehicle_type");
                sold = rs.getBoolean("sold");
                if (!sold) {
                    vehicles.add(new Vehicle(vin, year, price, make, model, color, odometer, sold, vehicleType));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;

    }

    //Retrieve Vehicle by year
    public List<Vehicle> findVehicleByYear(int minYear, int maxYear) {
        List<Vehicle> vehicles = new ArrayList<>();
        String vin, make, model, color, vehicleType;
        double price;
        int year, odometer;
        boolean sold;
        String query = """
                SELECT vin, year, make, model, color, vehicle_type, sold
                FROM vehicles
                WHERE year between ? AND ?
                ORDER BY year DESC;
                                
                """;
        try (Connection connection = datasource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, minYear);
            stmt.setInt(2, maxYear);
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                vin = rs.getString("vin");
                year = rs.getInt("year");
                make = rs.getString("make");
                model = rs.getString("model");
                price = rs.getDouble("price");
                color = rs.getString("color");
                odometer = rs.getInt("odometer");
                vehicleType = rs.getString("vehicle_type");
                sold = rs.getBoolean("sold");
                if (!sold) {
                    vehicles.add(new Vehicle(vin, year, price, make, model, color, odometer, sold, vehicleType));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;

        // Retrieve by color
        // retrieve vehicle by odometer (mileage)
        //Retrieve by vehicle type
    }
}