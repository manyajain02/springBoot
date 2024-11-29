package com.springboot.datamapping.Datamapping.repositories;

import com.springboot.datamapping.Datamapping.entities.DepartmentEntity;
import com.springboot.datamapping.Datamapping.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    DepartmentEntity findByManager(EmployeeEntity employeeEntity);
}
