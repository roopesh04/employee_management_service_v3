package com.example.employee_management_service.service;

import com.example.employee_management_service.dao.EmploymentDao;
import com.example.employee_management_service.dao.LeaveRequestDao;
import com.example.employee_management_service.models.LeaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LeaveService {

    @Autowired
    LeaveRequestDao leaveRequestDao;

    @Autowired
    EmploymentDao employmentDao;

    @Autowired
    EmployeeAuthService employeeAuthService;

    public List<LeaveRequest> findLeaveRequestsOfEmployee(String accessToken){
        return leaveRequestDao.findManyByEmpId(
                employeeAuthService.validateAccessToken(accessToken)
        );
    }

    public LeaveRequest saveLeaveRequest(LeaveRequest leaveRequest,String accessToken){
        leaveRequest.setEmpId(employeeAuthService.validateAccessToken(accessToken));
        leaveRequest.setManagerId(employmentDao.findEmployeeManagerId(leaveRequest.getEmpId()));
        return leaveRequestDao.save(leaveRequest);
    }

    public List<LeaveRequest> getLeaveRequests(String accessToken){
        String empId= employeeAuthService.validateAccessToken(accessToken);
        return leaveRequestDao.getLeaveRequests(empId);
    }

    public String updateLeaveRequest(String accessToken,LeaveRequest leaveRequestDTO){
        Optional<LeaveRequest> leaveRequest=leaveRequestDao.findById(leaveRequestDTO.get_id());
        String empId= employeeAuthService.validateAccessToken(accessToken);
        if(leaveRequest.isEmpty()) throw new RuntimeException("Please provide valid ID");
        if(!Objects.equals(empId, leaveRequest.get().getManagerId()))
            throw new RuntimeException("You are not allowed to update the leave request");
        if(leaveRequestDTO.getStatus()!=null){
            leaveRequest.get().setStatus(leaveRequestDTO.getStatus());
            leaveRequestDao.save(leaveRequest.get());
            return "Updated the Status";
        }
        throw new RuntimeException("Please provide the status");
    }
}
