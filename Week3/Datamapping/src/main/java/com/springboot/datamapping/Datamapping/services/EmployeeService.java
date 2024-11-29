package com.springboot.datamapping.Datamapping.services;

import com.springboot.datamapping.Datamapping.entities.DepartmentEntity;
import com.springboot.datamapping.Datamapping.entities.EmployeeEntity;
import com.springboot.datamapping.Datamapping.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity createNewEmployee(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    public EmployeeEntity getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }


}
