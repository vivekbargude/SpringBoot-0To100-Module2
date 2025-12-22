package com.example.module2.presentationLayer.controller;

import com.example.module2.persistentLayer.entity.EmployeeEntity;
import com.example.module2.persistentLayer.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

//    @GetMapping(path = "/get-message")
//    public String getMessage() {
//        return "Hello World";
//    }

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //    @GetMapping(path = "/employees/{employeeId}")
    @GetMapping(path = "/{employeeId}")
//    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId)
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long id) { //@PathVariable ensures that the value of path variable coming from client should be injected where this annotation is used.
        return employeeRepository.findById(id).orElse(null);
    }


//    @GetMapping(path = "/employees")
    @GetMapping()
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false) Integer age,
                                                @RequestParam(required = false) String sortBy
                                  ) { //@RequestParam indicates these are optional values and if passed get them and inject it here.
        return employeeRepository.findAll();
    }

    @PostMapping()
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employee) {
        return employeeRepository.save(employee);
    }
}
