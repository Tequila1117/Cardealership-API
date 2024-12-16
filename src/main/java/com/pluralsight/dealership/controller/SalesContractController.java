package com.pluralsight.dealership.controller;

import com.pluralsight.dealership.DAO.SalesContractDAO;
import com.pluralsight.dealership.model.SalesContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/API")
public class SalesContractController {

    private SalesContractDAO salesdata;

    @Autowired
    public SalesContractController(SalesContractDAO salesdata) {
        this.salesdata = salesdata;
    }
    @GetMapping("/FindSalesContract/{id}")
    public ArrayList<SalesContract> findSalesContract(@PathVariable("id") int id) {
        return salesdata.findSalesContract(id);
    }

    @PostMapping("/AddSalesContract")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addSalesContract(@RequestBody SalesContract contract) {
        salesdata.addSalesContract(contract);
    }
}
