package com.example.employee_management_service.dao;

import com.example.employee_management_service.models.Employee;
import com.example.employee_management_service.repository.EmployeeDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EmployeeDao {

    @Autowired
    EmployeeDetailsRepository employeeDetailsRepository;

    public Optional<Employee> findById(String empId){
        return employeeDetailsRepository.findById(empId);
    }

    public Employee save(Employee employee){
        return employeeDetailsRepository.save(employee);
    }

}
