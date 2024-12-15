package com.pluralsight.dealership.controller;

import com.pluralsight.dealership.DAO.DealershipDAO;
import com.pluralsight.dealership.model.Dealership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/API")


public class DealershipController {
    private DealershipDAO dealershipdata;

    @Autowired
    public DealershipController(DealershipDAO dealershipdata) {
        this.dealershipdata = dealershipdata;
    }

    @GetMapping("/GetAllDealerships")
    public List<Dealership> findAllDealerships() {
        return dealershipdata.findAllDealerships();
    }
}
