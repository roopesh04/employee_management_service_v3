package com.example.employee_management_service.controllers;

import com.example.employee_management_service.models.Employee;
import com.example.employee_management_service.service.EmployeeService;
import com.example.employee_management_service.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/{id}")
    public Employee findEmployeeDetails(@PathVariable("id") String empId,
                                        @RequestHeader(Constants.Authorization) String accessToken){
        return employeeService.findEmployeeDetails(empId,accessToken);
    }

    @GetMapping
    public Employee findEmployeeDetailsOnAccessToken(@RequestHeader(Constants.Authorization) String accessToken){
        return employeeService.findEmployeeDetailsOnAccessToken(accessToken);
    }

    @PostMapping
    public Employee saveEmployeeData(@RequestBody Employee employee,
                                     @RequestHeader(Constants.Authorization) String accessToken){
        return employeeService.insertEmployeeDetails(employee,accessToken);
    }
}
