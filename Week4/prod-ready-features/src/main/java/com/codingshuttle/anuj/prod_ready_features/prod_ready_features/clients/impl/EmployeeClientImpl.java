package com.codingshuttle.anuj.prod_ready_features.prod_ready_features.clients.impl;

import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.advice.ApiResponse;
import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.clients.EmployeeClient;
import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);
    @Override

    public List<EmployeeDTO> getAllEmployees() {

        log.trace("Trying to retrieve all the employees in getAllEmployees");    // need to ena ble
        try {
//            log.error("error log ");  //--
//            log.warn("warn log");     //default errors
//            log.info("info log");  // --
            log.info("Attempting to call the restClient Method in getAllEmployees");
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfully retrieved the employees in getAllEmployees");
            log.trace("Retrieved employee list in getAllEmployees {}", employeeDTOList.getData());
            return employeeDTOList.getData();
        } catch (Exception ex) {
            log.error("Exception occurred in getAllEmployees", ex);
            throw new RuntimeException(ex);
        }

    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        log.trace("Trying to get Employee By Id in getEmployeeId with id: {}",employeeId);
        try {
            ApiResponse<EmployeeDTO> employeeResponse = restClient.get()
                    .uri("employees/{employeeId}", employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeResponse.getData();
        }catch (Exception ex){
            log.error("Exception occurred in getAllEmployees", ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        log.trace("Trying to create Employee with information {}", employeeDTO);
        try{
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)  // request body pass
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res)->{
                        System.out.println("Error occurred " + new String(res.getBody().readAllBytes()));
                        throw new RuntimeException("could not create the employee");
                    })
//                    .body(new ParameterizedTypeReference<>() {   // can remove body and add toEntity
//                    });
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
            log.trace("Successfully created a new employee :{}",employeeDTOApiResponse.getBody());
            return employeeDTOApiResponse.getBody().getData();
        } catch (Exception ex) {
            log.error("Exception occurred in ",ex);
          throw new RuntimeException(ex);
        }

    }
}