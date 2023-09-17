package com.example.employee_management_service.service;

import com.example.employee_management_service.dao.EmployeeDao;
import com.example.employee_management_service.dao.EmploymentDao;
import com.example.employee_management_service.models.Employee;
import com.example.employee_management_service.utils.Constants;
import com.example.employee_management_service.utils.PasswordHashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    PasswordHashUtil passwordHashUtil;

    @Autowired
    EmployeeAuthService employeeAuthService;

    @Autowired
    EmploymentDao employmentDao;

    public Employee findEmployeeDetails(String empId,String accessToken){
        employeeAuthService.validateAccessToken(accessToken);
        Optional<Employee> employee=employeeDao.findById(empId);

        if(employee.isPresent()) return employee.get();
        return null;
    }

    public Employee insertEmployeeDetails(Employee employee,String accessToken){
        employee.setPassword(passwordHashUtil.encodePassword(Constants.defaultPassword));
        String empId=employeeAuthService.validateAccessToken(accessToken);
        Boolean checkIfTheEmployeeIsHR=employmentDao.checkIfTheEmployeeIsHR(empId);
        if(!checkIfTheEmployeeIsHR) throw new RuntimeException("You are not allowed to create Employee");
        return employeeDao.save(employee);
    }

    public Employee findEmployeeDetailsOnAccessToken(String accessToken){
        return employeeDao.findById(employeeAuthService.validateAccessToken(accessToken)).get();
    }
}
