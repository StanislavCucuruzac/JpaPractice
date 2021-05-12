package com.example.demo.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EmployeeServiceTest {
    @Autowired
    EmployeeService employeeService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByName() {
    }

    @Test
    void getAll() {
        assertNotNull(employeeService.getAll());
        assertEquals(10,employeeService.getAll().size());

    }

    @Test
    void getByExperience() {
    }

    @Test
    void getEmployeeByAge() {
    }

    @Test
    void getCriteriaNames() {
    }

    @Test
    void getCriteriaAge() {
    }
}