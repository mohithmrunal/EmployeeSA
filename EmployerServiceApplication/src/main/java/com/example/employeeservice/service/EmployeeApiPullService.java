package com.example.employeeservice.service;

import com.example.employeeservice.dto.EmployeeApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class EmployeeApiPullService {

    private final RestClient restClient;
    private final EmployeeProcedureService employeeProcedureService;
    private final String employeeApiUrl;

    public EmployeeApiPullService(
            RestClient.Builder restClientBuilder,
            EmployeeProcedureService employeeProcedureService,
            @Value("${external.employee-api.url}") String employeeApiUrl
    ) {
        this.restClient = restClientBuilder.build();
        this.employeeProcedureService = employeeProcedureService;
        this.employeeApiUrl = employeeApiUrl;
    }

    public int pullEmployeesFromApi() {
        EmployeeApiResponse[] employees = restClient.get()
                .uri(employeeApiUrl)
                .retrieve()
                .body(EmployeeApiResponse[].class);

        if (employees == null) {
            return 0;
        }

        for (EmployeeApiResponse employee : employees) {
            employeeProcedureService.saveEmployee(employee);
        }

        return employees.length;
    }
}
