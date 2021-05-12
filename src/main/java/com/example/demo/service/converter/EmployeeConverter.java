package com.example.demo.service.converter;

import com.example.demo.model.EmployeeEntity;
import com.example.demo.service.dto.EmployeeDto;

public class EmployeeConverter {
    public static EmployeeDto toEmployeeDto(EmployeeEntity employeeEntity){
        return new EmployeeDto(employeeEntity.getName(), employeeEntity.getAge(), employeeEntity.getExperience());
    }
    public static EmployeeEntity toEmployeeEntity(EmployeeDto employeeDto){
        return EmployeeEntity.builder()
                .name(employeeDto.getName())
                .age(employeeDto.getAge())
                .experience(employeeDto.getExperience())
                .build();
    }


}
