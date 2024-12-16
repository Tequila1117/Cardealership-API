//package com.pluralsight.dealership.DAO;
//
//import com.pluralsight.dealership.model.Dealership;
//
//import javax.sql.DataSource;
//import java.sql.*;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class DealershipDAO {
//    private DataSource datasource;
//
//    // Constructor
//    public DealershipDAO(DataSource datasource) {
//        this.datasource = datasource;
//    }
//
//    // Retrieve dealership by ID
//    public Dealership findDealershipById(int id) {
//        String name = "";
//        String address = "";
//        String phone = "";
//        String query = """
//                SELECT * from dealership
//                WHERE dealership_id = ?;
//                 """;
//        try (Connection connection = datasource.getConnection()) {
//            PreparedStatement stmt = connection.prepareStatement(query);
//            stmt.setInt(1, id);
//            ResultSet rs = stmt.getResultSet();
//
//            if (rs.next()) {
//                rs.getString("name");
//                rs.getString("address");
//                rs.getString("phone");
//
//            }
//            return new Dealership(id, name, address, phone);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
//
//    public List<Dealership> findAllDealerships() {
//        List<Dealership> dealerships = new ArrayList<>();
//        String query = "SELECT * from dealership";
//
//        try (Connection connection = datasource.getConnection();
//             Statement stmt = connection.createStatement();
//             ResultSet rs = stmt.executeQuery(query)) {
//
//            while (rs.next()) {
//                Dealership dealership = new Dealership(
//                        rs.getInt("dealership_id"),
//                        rs.getString("name"),
//                        rs.getString("address"),
//                        rs.getString("phone")
//                );
//                dealerships.add(dealership);
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        return dealerships;
//
//    }
//}



