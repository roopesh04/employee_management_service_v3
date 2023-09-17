package com.example.employee_management_service.service;

import com.example.employee_management_service.dao.EmploymentDao;
import com.example.employee_management_service.models.Employment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmploymentService {

    @Autowired
    EmploymentDao employmentDao;

    @Autowired
    EmployeeAuthService employeeAuthService;

    public Employment saveEmploymentDetails(Employment employee,String accessToken){
        String empId=employeeAuthService.validateAccessToken(accessToken);
        Boolean checkIfTheEmployeeIsHR=employmentDao.checkIfTheEmployeeIsHR(empId);
        if(!checkIfTheEmployeeIsHR) throw new RuntimeException("You are not allowed to create Employee");
        return employmentDao.save(employee);
    }

    public Employment findEmplomentDetmails(String empId){
        Optional<Employment> employmentDetails = employmentDao.findByEmpId(empId);

        if(employmentDetails.isPresent()) return employmentDetails.get();
        return null;
    }

    public Employment getEmploymentOnAccessToken(String accessToken){
        return employmentDao.findByEmpId(employeeAuthService.validateAccessToken(accessToken)).get();
    }
}
