package com.example.employee_management_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.UUID;

@Service
public class EmployeeAuthService {

    @Autowired
    RedisTemplate redisTemplate;

    public String generateAccessTokenForUser(String empId){
        String accessToken= UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(accessToken,empId);
        redisTemplate.expire(accessToken, Duration.ofSeconds(6000));
        return accessToken;
    }

    public String validateAccessToken(String accessToken){
        Object empId= redisTemplate.opsForValue().get(accessToken);
        if(empId==null) throw new RuntimeException("Invalid AccessToken");
        return empId.toString();
    }

    public void deleteAccessToken(String accessToken){
        redisTemplate.expire(accessToken,Duration.ofSeconds(1));
    }
}
