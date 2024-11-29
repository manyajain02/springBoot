package com.week2.introMvc.Mvc.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="employees")
public class EmployeeEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO) /// auto will choose directly from the SEQUENCE and IDENTITY
        private Long id;
        private String name;
        private String email;
        private Integer age;
        private LocalDate dateOfJoining;
        private Boolean isActive;

    }

