package com.example.employee_management_service.repository;

import com.example.employee_management_service.models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailsRepository extends MongoRepository<Employee,String> {


}
