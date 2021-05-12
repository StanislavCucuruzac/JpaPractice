package com.example.demo.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateEmployeeRequest {

    private String name;

    private int age;

    private int experience;

}
