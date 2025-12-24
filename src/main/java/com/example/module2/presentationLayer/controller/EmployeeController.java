package com.example.module2.presentationLayer.controller;

import com.example.module2.exception.ResourceNotFoundException;
import com.example.module2.presentationLayer.dto.EmployeeDTO;
import com.example.module2.serviceLayer.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id) { //@PathVariable ensures that the value of path variable coming from client should be injected where this annotation is used.
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);

        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(()-> new ResourceNotFoundException("Employee Not Found with id: "+ id));

//        return ResponseEntity.ok(employeeDTO);
    }


//    @GetMapping(path = "/employees")
    @GetMapping()
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age,
                                                @RequestParam(required = false) String sortBy
                                  ) { //@RequestParam indicates these are optional values and if passed get them and inject it here.
        return ResponseEntity.ok(employeeService.findAllEmployees());
    }



    @PostMapping()
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO employee) {
        EmployeeDTO savedEmployee = employeeService.createNewEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO, @PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId,employeeDTO));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId) {
        boolean isDeleted = employeeService.deleteEmployeeById(employeeId);

        if(isDeleted)
            return ResponseEntity.ok(true);

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String,Object> updates,
                                                 @PathVariable Long employeeId) {
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId,updates);

        if(employeeDTO == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(employeeDTO);

    }
}
