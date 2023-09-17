package com.example.employee_management_service.controllers;

import com.example.employee_management_service.models.LeaveRequest;
import com.example.employee_management_service.service.LeaveService;
import com.example.employee_management_service.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/leave")
@CrossOrigin(origins = "*")
public class LeaveController {

    @Autowired
    LeaveService leaveService;

    @GetMapping
    public List<LeaveRequest> getEmployeeLeaveRequests
            (@RequestHeader(Constants.Authorization) String accessToken){
        return leaveService.findLeaveRequestsOfEmployee(accessToken);
    }

    @PostMapping
    public LeaveRequest saveTheLeaveRequest
            (@RequestBody LeaveRequest leaveRequest,
             @RequestHeader(Constants.Authorization) String accessToken){
        return leaveService.saveLeaveRequest(leaveRequest,accessToken);
    }

    @PostMapping("/leave-details")
    public List<LeaveRequest> getLeaveDetails(@RequestHeader(Constants.Authorization) String accessToken){
        return leaveService.getLeaveRequests(accessToken);
    }

    @PostMapping("/update-leave")
    public String updateLeaveRequest(
            @RequestBody LeaveRequest leaveRequest,
            @RequestHeader(Constants.Authorization) String accessToken){
        return leaveService.updateLeaveRequest(accessToken,leaveRequest);
    }
}
