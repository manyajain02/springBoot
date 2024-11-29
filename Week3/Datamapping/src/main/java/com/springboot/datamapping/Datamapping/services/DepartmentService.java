package com.springboot.datamapping.Datamapping.services;

import com.springboot.datamapping.Datamapping.entities.DepartmentEntity;
import com.springboot.datamapping.Datamapping.entities.EmployeeEntity;
import com.springboot.datamapping.Datamapping.repositories.DepartmentRepository;
import com.springboot.datamapping.Datamapping.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {
    
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public DepartmentEntity createNewDepartment(DepartmentEntity departmentEntity) {

        return  departmentRepository.save(departmentEntity);
    }

    public DepartmentEntity getDepartmentById(Long id) {

        return departmentRepository.findById(id).orElse(null);
    }

    public DepartmentEntity assignManagerToDepartment(Long employeeId, Long departmentId) {

        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
        return departmentEntity.flatMap(departmentEntity1 ->
                employeeEntity.map(employeeEntity1 -> {
                    departmentEntity1.setManager(employeeEntity1);
                    return departmentRepository.save(departmentEntity1);
                })).orElse(null);
    }
    public DepartmentEntity getAssignedDepartmentOfManager(Long employeeId) { // getassignedManagerByEmployeeId
//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);\
//        return employeeEntity.map(employee->employee.getManagedDepartment()).orElse(null);

//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);\
//          return departmentRepository.findByManager(employeeEntity.get());

        EmployeeEntity employeeEntity = EmployeeEntity.builder().id(employeeId).build();
        return departmentRepository.findByManager(employeeEntity);
    }
    public DepartmentEntity assignWorkerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee -> {
                    employee.setWorkerDepartment(department);
                    employeeRepository.save(employee);

                    department.getWorkers().add(employee);
                    return department;
                })).orElse(null);
    }


    public DepartmentEntity assignFreelancerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee -> {
                    employee.getFreelancerDepartments().add(department);
                    employeeRepository.save(employee);

                    department.getFreelancers().add(employee);
                    return department;
                })).orElse(null);
    }
}
