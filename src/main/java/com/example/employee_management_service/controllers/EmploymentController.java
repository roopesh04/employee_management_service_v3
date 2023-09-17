package com.example.employee_management_service.controllers;

import com.example.employee_management_service.models.Employment;
import com.example.employee_management_service.service.EmploymentService;
import com.example.employee_management_service.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employment")
@CrossOrigin(origins = "*")
public class EmploymentController {

    @Autowired
    EmploymentService employmentService;


    @GetMapping("/{id}")
    public Employment findEmployeeDetails(@PathVariable("id") String empId){
        return employmentService.findEmplomentDetmails(empId);
    }

    @GetMapping
    public Employment findEmployeeByAccessToken(@RequestHeader(Constants.Authorization) String accessToken){
        return employmentService.getEmploymentOnAccessToken(accessToken);
    }

    @PostMapping
    public Employment saveEmployeeData(@RequestBody Employment employment,
                                       @RequestHeader(Constants.Authorization) String accessToken){
        return employmentService.saveEmploymentDetails(employment,accessToken);
    }

}
