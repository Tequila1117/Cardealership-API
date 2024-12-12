package com.pluralsight.dealership.controller;


import com.pluralsight.dealership.DAO.VehicleDAO;
import com.pluralsight.dealership.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/API")

public class VehicleController {
    private VehicleDAO vehicledata;

    @Autowired
    public VehicleController(VehicleDAO vehicledata) {
        this.vehicledata = vehicledata;
    }

    @GetMapping ("/GetAllVehicles")
    public List<Vehicle> getAllVehicles() {
        return vehicledata.getAllVehicles();



    }
}
