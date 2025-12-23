package com.example.module2.presentationLayer.controller;

import com.example.module2.persistentLayer.entity.EmployeeEntity;
import com.example.module2.persistentLayer.repository.EmployeeRepository;
import com.example.module2.presentationLayer.dto.EmployeeDTO;
import com.example.module2.serviceLayer.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

//    @GetMapping(path = "/get-message")
//    public String getMessage() {
//        return "Hello World";
//    }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService service) {
        this.employeeService = service;
    }

    //    @GetMapping(path = "/employees/{employeeId}")
    @GetMapping(path = "/{employeeId}")
//    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId)
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id) { //@PathVariable ensures that the value of path variable coming from client should be injected where this annotation is used.
        return employeeService.getEmployeeById(id);
    }


//    @GetMapping(path = "/employees")
    @GetMapping()
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false) Integer age,
                                                @RequestParam(required = false) String sortBy
                                  ) { //@RequestParam indicates these are optional values and if passed get them and inject it here.
        return employeeService.findAllEmployees();
    }

    @PostMapping()
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employee) {
        return employeeService.createNewEmployee(employee);
    }
}
