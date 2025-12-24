package com.example.module2.serviceLayer.services;

import com.example.module2.exception.ResourceNotFoundException;
import com.example.module2.persistentLayer.entity.EmployeeEntity;
import com.example.module2.persistentLayer.repository.EmployeeRepository;
import com.example.module2.presentationLayer.dto.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
//        return employeeEntity.map(entity -> modelMapper.map(entity,EmployeeDTO.class));

        return employeeRepository.findById(id)
                .map(employee -> modelMapper.map(employee,EmployeeDTO.class));
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

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        isExists(employeeId);

        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO,EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEntity, EmployeeDTO.class);
    }

    public void isExists(Long employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        if(!exists) throw new ResourceNotFoundException("Employee Not Found with id: "+employeeId);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        isExists(employeeId);

        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        isExists(employeeId);

        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field,value)->{
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
        });

        EmployeeEntity updatedEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(updatedEntity,EmployeeDTO.class);
    }
}
