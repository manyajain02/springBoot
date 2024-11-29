package com.springboot.datamapping.Datamapping.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "employee_table")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "manager")
    @JsonIgnore /// to solve the recursiveness in reponse json
    private DepartmentEntity managedDepartment;

    @ManyToOne(cascade = CascadeType.ALL)   // name = "worker_department_id": This is the name of the column in the employee_table that acts as the foreign key.
                // It stores the ID of the department to which the employee belongs.
//                referencedColumnName = "id": This refers to the primary key of the DepartmentEntity class.
//                The worker_department_id in the employee_table references the id column in the department_table
    @JoinColumn(name = "worker_department_id",referencedColumnName = "id")   // @JoinColoumn This annotation is used to specify the foreign key column in the employee_table. In this case:
    @JsonIgnore
    private DepartmentEntity workerDepartment;      //  Foreign key for the worke // contains a reference to the department they belong to.

//    @ManyToOne: This annotation is placed on the workerDepartment field in EmployeeEntity.
//    It signifies that many instances of EmployeeEntity can relate to
//    one instance of DepartmentEntity.
//    This indicates that multiple employees can share the same department.

    @ManyToMany
    @JoinTable(name = "freelancer_departments_mapping",
    joinColumns = @JoinColumn(name = "employee_id"),
    inverseJoinColumns = @JoinColumn(name = "department_id"))
    @JsonIgnore
    private Set<DepartmentEntity> freelancerDepartments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }


//    @ManyToOne(cascade =  CascadeType.ALL)
////    @JoinColumn(name = "worker_department_id")
//    @JoinTable(name = "worker_department_mapping")
//    @JsonIgnore
//    private DepartmentEntity workerDepartment;

}
