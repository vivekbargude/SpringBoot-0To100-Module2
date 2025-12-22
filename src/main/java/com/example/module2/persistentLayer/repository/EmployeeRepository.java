package com.example.module2.persistentLayer.repository;


//We can't perform operations directly on the entities we need repositories for that purpose.
//All business logic to access and modify data is written here.
//It communicates with the DB.
//Previously we have to define the implementations of methods to modify(CRUD) the data manually now it is done auto by JPA Repository(we don't need to provide definitions).

import com.example.module2.persistentLayer.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//Creates a bean for DI where-ever needed.
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {

    //We don't need to define it is defined auto by data jpa.

}
