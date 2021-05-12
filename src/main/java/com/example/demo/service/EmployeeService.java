package com.example.demo.service;


import com.example.demo.model.EmployeeEntity;
import com.example.demo.repository.EmployeeDao;
import com.example.demo.service.converter.EmployeeConverter;
import com.example.demo.service.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeService {

   private EmployeeDao employeeDao;

    public EmployeeDto save(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = EmployeeConverter.toEmployeeEntity(employeeDto);
        EmployeeEntity savedEntity = employeeDao.save(employeeEntity);
        return EmployeeConverter.toEmployeeDto(savedEntity);
    }
    public EmployeeDto findById(int id){
        return EmployeeConverter.toEmployeeDto(employeeDao.findById(id));
    }
    public List<EmployeeDto> findByName(String name){
        return employeeDao.findByName(name).stream()
                .map(EmployeeConverter::toEmployeeDto)
                .collect(toList());
    }
    public List<EmployeeDto> getAll(){
        return employeeDao.findAllSQL().stream()
                .map(EmployeeConverter::toEmployeeDto)
                .collect(toList());
    }
    public List<EmployeeDto> getByExperience(int experience){
        return employeeDao.getEmployeeByExperienceWithNamedQuery(experience).stream()
                .map(EmployeeConverter::toEmployeeDto)
                .collect(toList());
    }

    public List<EmployeeDto> getEmployeeByAge(int age){
        return employeeDao.getEmployeeByAgeWithNamedQuery(age).stream()
                .map(EmployeeConverter::toEmployeeDto)
                .collect(toList());
    }
    public List<String> getCriteriaNames(){
        return employeeDao.getCriteriaEmployeeNames();

    }
    public List<EmployeeDto> getCriteriaAge(){
        return employeeDao.getCriteriaAge().stream()
                .map(EmployeeConverter::toEmployeeDto)
                .collect(toList());
    }







}
