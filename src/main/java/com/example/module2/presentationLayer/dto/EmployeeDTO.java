package com.example.module2.presentationLayer.dto;

import com.example.module2.annotation.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

//DTO's are teh communication entities, or we can call them as simple java object which is used in controller layer.
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;
    @NotBlank(message = "Name can't be blank.")
    @Size(min = 3, max = 10, message = "Character should range from 3 to 10 only.")
    private String name;

    @NotBlank(message = "Email can't be blank.")
    @Email(message = "Email should be a valid email.")
    private String email;

    @Positive(message = "Age can't be negative.")
    @NotNull(message = "Age can't be null.")
    @Max(value = 80, message = "Age can't be more than 80.")
    @Min(value = 18, message = "Age can't be less than 18.")
    private Integer age;

    @NotBlank(message = "Role can't be blank.")
//    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role can be only ADMIN or ROLE.")
    @EmployeeRoleValidation()
    private String role; //ADMIN , USER

    @PastOrPresent(message = "Date of Joining can't be in the future.")
    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    @AssertTrue(message = "Employee has to be active.")
    private Boolean isActive;

    @Positive(message = "Salary can't be negative.")
    @NotNull(message = "Salary can't be null.")
    @Digits(integer = 6, fraction = 2, message = "Salary can be in the form XXXXXX.YY")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50")
    private Double salary;

}
