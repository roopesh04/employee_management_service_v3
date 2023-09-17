package com.example.employee_management_service.dao;

import com.example.employee_management_service.models.LeaveRequest;
import com.example.employee_management_service.repository.LeaveRequestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LeaveRequestDao {

    @Autowired
    LeaveRequestsRepository leaveRequestsRepository;

    public List<LeaveRequest> findManyByEmpId(String empId) {
        return leaveRequestsRepository.findManyByEmpId(empId);
    }

    public LeaveRequest save(LeaveRequest leaveRequest){
        return leaveRequestsRepository.save(leaveRequest);
    }

    public List<LeaveRequest> getLeaveRequests(String empId){
        return leaveRequestsRepository.findManyByManagerId(empId);
    }

    public Optional<LeaveRequest> findById(String id){
        return leaveRequestsRepository.findById(id);
    }
}
