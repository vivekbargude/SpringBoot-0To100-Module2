package com.example.module2.persistentLayer.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

//Can be used for declaring constraints on the table etc unlike dto which can be used for validation and request response.
//DTO can be subset of entity which has only field that need to be shown to other layers.
//Cant perform operations directly on the entities we need repositories for that purpose.


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees") //If not specified , the class name will be set as table name.
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;
    private String role;
    @JsonProperty("isActive")
    private Boolean isActive;
    private Double salary;


}
