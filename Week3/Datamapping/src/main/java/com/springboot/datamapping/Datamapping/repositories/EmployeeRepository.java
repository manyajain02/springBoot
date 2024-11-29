package com.springboot.datamapping.Datamapping.repositories;

import com.springboot.datamapping.Datamapping.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
