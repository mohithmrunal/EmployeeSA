package com.example.employeeservice.service;

import com.example.employeeservice.dto.EmployeeApiResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProcedureService {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeProcedureService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveEmployee(EmployeeApiResponse employee) {
        jdbcTemplate.update(
                "{call SAVE_EMPLOYEE(?, ?, ?, ?, ?)}",
                employee.getEmployeeId(),
                employee.getName(),
                employee.getEmployeeRole(),
                employee.getEmailAddress(),
                employee.getPhoneNumber()
        );
    }
}
