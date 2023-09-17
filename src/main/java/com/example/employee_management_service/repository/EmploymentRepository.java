package com.example.employee_management_service.repository;

import com.example.employee_management_service.models.Employment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmploymentRepository extends MongoRepository<Employment,String> {

    Optional<Employment> findByEmpId(String empId);
}
