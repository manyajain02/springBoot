package com.springboot.datamapping.Datamapping.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "department_table")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToOne
    @JoinColumn(name ="department_manager")    // foreign key in department
    private EmployeeEntity manager;

    @OneToMany(mappedBy="workerDepartment", fetch = FetchType.LAZY)
    private Set<EmployeeEntity> workers;

    @ManyToMany(mappedBy="freelancerDepartments")
    private Set<EmployeeEntity> freelancers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEntity that = (DepartmentEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(), that.getTitle());
    }

    @Override  /// added hash code for unidirectional
    public int hashCode() {
        return Objects.hash(getId(), getTitle());
    }
//    @OneToMany(mappedBy = "workerDepartment", fetch = FetchType.LAZY)
//    private Set<EmployeeEntity> workers;


}

//Summary
//The @ManyToOne mapping allows you to associate multiple instances of EmployeeEntity with a single DepartmentEntity.
//The foreign key (worker_department_id) in the employee_table references the primary key (id) in the department_table.
//This mapping is useful for establishing relationships between entities in a relational database, making it easy to navigate and query related data.