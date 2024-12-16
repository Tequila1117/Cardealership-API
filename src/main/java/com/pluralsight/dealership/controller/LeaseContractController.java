package com.pluralsight.dealership.controller;
import com.pluralsight.dealership.DAO.LeaseContractDAO;
import com.pluralsight.dealership.model.LeaseContract;
import com.pluralsight.dealership.model.SalesContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.rmi.dgc.Lease;
import java.util.ArrayList;

@RestController
@RequestMapping("/API")

public class LeaseContractController {
    private LeaseContractDAO leasedata;

    @Autowired
    public LeaseContractController(LeaseContractDAO leasedata) {
        this.leasedata = leasedata;
    }
    @GetMapping("/FindLeaseContract/{id}")
    public LeaseContract findLeaseContract(@PathVariable("id") int id) {
        return (LeaseContract) leasedata.findLeaseContract(id);
    }
    @PostMapping("/AddLeaseContract")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addLeaseContract(@RequestBody LeaseContract contract) {
        leasedata.addLeaseContract(contract);
    }


}
