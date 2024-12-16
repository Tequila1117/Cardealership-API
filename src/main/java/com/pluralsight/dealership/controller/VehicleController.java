package com.pluralsight.dealership.controller;


import com.pluralsight.dealership.DAO.VehicleDAO;
import com.pluralsight.dealership.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/API")

public class VehicleController {
    private VehicleDAO vehicledata;

    @Autowired
    public VehicleController(VehicleDAO vehicledata) {
        this.vehicledata = vehicledata;
    }

    @GetMapping("/GetAllVehicles")
    public List<Vehicle> getAllVehicles() {
        return vehicledata.getAllVehicles();


    }

    @GetMapping("/FindVehiclesByDealership/{id}")
    public List<Vehicle> findVehiclesByDealership(@PathVariable("id") int id) {
        return vehicledata.findVehiclesByDealership(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
     @PostMapping("/CreateVehicle")
    public void createVehicle(@RequestBody Vehicle vehicle) {
    vehicledata.createVehicle(vehicle);


    }
    @GetMapping("/VehicleMakeModel")
    public List<Vehicle> findVehiclesByMakeModel(@RequestParam("make") String make, @RequestParam("model") String model) {

        return vehicledata.findVehiclesByMakeModel(make, model);
    }

    @PutMapping("/UpdateVehicle/{vin}")
    public void updateVehicle(@RequestBody Vehicle vehicle, @PathVariable("vin") String vin) {
        vehicledata.updateVehicle(vehicle, vin);

    }

    @GetMapping("VehicleByVin/{vin}")
    public Vehicle findVehiclesByVin(@PathVariable("vin") String vin) {
        return vehicledata.findVehiclesByVin(vin);
    }

    @DeleteMapping("/DeleteVehicle/{vin}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable("vin") String vin) {
        vehicledata.deleteVehicle(vin);
    }

    @GetMapping("/VehiclePriceRange")
    public List<Vehicle> findVehiclesByPriceRange(@RequestParam("minYear") double minPrice, @RequestParam("maxYear") double maxPrice) {
        return vehicledata.findVehiclesByPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/VehicleByYear")
    public List<Vehicle> findVehiclesByYear(@RequestParam("minYear") int minYear, @RequestParam("maxYear") int maxYear) {
        return vehicledata.findVehiclesByYear(minYear, maxYear);
    }

    @GetMapping("/VehicleByColor")
    public List<Vehicle> findVehiclesByColor(@RequestParam("color") String color) {
        return vehicledata.findVehiclesByColor(color);
    }

    @GetMapping("/VehicleByMiles")
    public List<Vehicle> findVehiclesByMiles(@RequestParam("minMiles") int minMiles, @RequestParam("maxMiles") int maxMiles) {
        return vehicledata.findVehiclesByMiles(minMiles, maxMiles);
    }
}
