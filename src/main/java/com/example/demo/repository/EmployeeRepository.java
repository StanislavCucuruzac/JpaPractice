package com.example.demo.repository;

import com.example.demo.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer>, JpaSpecificationExecutor<EmployeeEntity> {
    Optional<EmployeeEntity> findByName(String name);

    List<EmployeeEntity> findByAge(int age);

    List<EmployeeEntity> findAllByName(String name);

    List<EmployeeEntity> findAllByNameLike(String name);

}
