package com.example.demo.controller;

import com.example.demo.model.EmployeeEntity;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    EmployeeRepository employeeRepository;
//    @MockBean
//    EmployeeService employeeService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<List<EmployeeEntity>> responseEntity = testRestTemplate.exchange(
                "/api/employee", HttpMethod.GET, entity, new ParameterizedTypeReference<List<EmployeeEntity>>(){});
        List<EmployeeEntity> list = responseEntity.getBody();
        assertEquals(6,list.size());
    }

    @Test
    void createEmployee(EmployeeEntity employeeEntity) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<EmployeeEntity> entity = new HttpEntity<EmployeeEntity>(employeeEntity,headers);
    }

    @Test
    void test1() {


    }

    @Test
    void getById() {
        HttpHeaders headers= new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<EmployeeEntity> entity = new HttpEntity<>(headers);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "2");
        ResponseEntity<EmployeeEntity> responseEntity = testRestTemplate.getForEntity(
                "/api/employee/"+2, EmployeeEntity.class);
        EmployeeEntity employee = responseEntity.getBody();
        assertEquals("Vasili", employee.getName());
    }
    @Test
    void getByName(){
        EmployeeEntity employeeEntity =new EmployeeEntity(5,"Petr", 27, 3);
        employeeRepository.save(employeeEntity);
        String name = employeeEntity.getName();
        ResponseEntity<EmployeeEntity[]> responseEntity =
                testRestTemplate.getForEntity(
                        "/api/employee/name/{name}", EmployeeEntity[].class, name);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(name, Objects.requireNonNull(responseEntity.getBody()[0].getName()));
     //   assertEquals("Petr", responseEntity.getBody().getName());
    }

    @Test
    void getByExperience() {
        EmployeeEntity employeeEntity =new EmployeeEntity(5,"Petr", 27, 3);
        employeeRepository.save(employeeEntity);
        int experience = employeeEntity.getExperience();
        ResponseEntity<EmployeeEntity[]> responseEntity =
                testRestTemplate.getForEntity(
                        "/api/employee/criteria/byExperience{experience}", EmployeeEntity[].class, experience);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(experience, responseEntity.getBody()[0].getExperience());
    }

}