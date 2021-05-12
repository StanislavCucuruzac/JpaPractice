package com.example.demo.repository;

import com.example.demo.model.EmployeeEntity;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmployeeDaoTest {
    @Mock
    EntityManager entityManager;
    @InjectMocks
    EmployeeDao employeeDao = new EmployeeDao();

    List<EmployeeEntity> list= new ArrayList<>();
    EmployeeEntity employeeEntity1;
    EmployeeEntity employeeEntity2;
    @BeforeEach
    void setUp() {
        employeeEntity1 = new EmployeeEntity(1, "Nel", 23,2);
        employeeEntity2 = new EmployeeEntity(2, "Val", 26,4);
        list.add(employeeEntity1);
        list.add(employeeEntity2);
    }

    @AfterEach
    void tearDown() {
        list.clear();
        employeeEntity1 = null;
        employeeEntity2 = null;
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
    void findAllSQL() {
//        Query mockedQuery = mock(Query.class);
//        when(mockedQuery.getResultList()).thenReturn(list);
//        assertEquals(2, employeeDao.findAllSQL());

    }

    @Test
    void getEmployeeByExperienceWithNamedQuery() {
    }

    @Test
    void getEmployeeByAgeWithNamedQuery() {
    }

    @Test
    void getCriteriaEmployeeNames() {
    }

    @Test
    void getCriteriaAge() {
    }
}