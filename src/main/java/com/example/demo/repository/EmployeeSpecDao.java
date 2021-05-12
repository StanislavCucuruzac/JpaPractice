package com.example.demo.repository;

import com.example.demo.model.EmployeeEntity;
import com.example.demo.model.EmployeeEntitySpec;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.demo.model.EmployeeEntitySpec.getByName;
import static com.example.demo.model.EmployeeEntitySpec.searchLikeName;

@Service
@AllArgsConstructor
public class EmployeeSpecDao {

    EmployeeRepository employeeRepository;

    public List<EmployeeEntity> getEmployeeByName(String name) {
        return employeeRepository.findAll(getByName(name));
    }

    public List<EmployeeEntity> searchEmployeeLikeName(String searchOne) {
        return employeeRepository.findAll(searchLikeName(searchOne));
    }
}
