package com.example.module2.presentationLayer.controller;

import com.example.module2.presentationLayer.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

//    @GetMapping(path = "/get-message")
//    public String getMessage() {
//        return "Hello World";
//    }



//    @GetMapping(path = "/employees/{employeeId}")
    @GetMapping(path = "/{employeeId}")
//    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId)
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id) { //@PathVariable ensures that the value of path variable coming from client should be injected where this annotation is used.
        return new EmployeeDTO(
                id,
                "Vivek",
                "vivek@gmail.com",
                22,
                LocalDate.of(2024,12,12),
                true
                );
    }


//    @GetMapping(path = "/employees")
    @GetMapping()
    public String getAllEmployees(@RequestParam(required = false) Integer age,
                                  @RequestParam(required = false) String sortBy
                                  ) { //@RequestParam indicates these are optional values and if passed get them and inject it here.
        return "Hi age "+age+" sorted by "+sortBy;
    }

    @PostMapping()
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeDTO.setId(1L);
        return employeeDTO;
    }
}
