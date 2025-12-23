package com.example.module2.serviceLayer.services;

import com.example.module2.persistentLayer.entity.EmployeeEntity;
import com.example.module2.persistentLayer.repository.EmployeeRepository;
import com.example.module2.presentationLayer.dto.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Service -> Makes it a bean so that it can be injected in the place where-ever needed.
//Also does to transform the data from dto to entity and vice-versa.
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> findAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employee -> modelMapper.map(employee,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO employee) {
        EmployeeEntity newEmployee = modelMapper.map(employee,EmployeeEntity.class);
        EmployeeEntity saved = employeeRepository.save(newEmployee);
        return modelMapper.map(saved,EmployeeDTO.class);
    }
}
