package com.example.demo.controller;

import com.example.demo.controller.request.CreateEmployeeRequest;
import com.example.demo.controller.response.CreateEmployeeResponse;
import com.example.demo.model.EmployeeEntity;
import com.example.demo.model.EmployeeEntitySpec;
import com.example.demo.repository.EmployeeDao;
import com.example.demo.repository.EmployeeSpecDao;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.dto.EmployeeDto;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "Swagger2DemoRestController", description = "REST APIs related to Employee Entity")
@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

     private final EmployeeSpecDao employeeSpecDao;


    @GetMapping
    public List<EmployeeDto> findAll() {
        return employeeService.getAll();
    }

    @PostMapping
    public CreateEmployeeResponse createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
        EmployeeDto employeeDto = new EmployeeDto(createEmployeeRequest.getName(), createEmployeeRequest.getAge(), createEmployeeRequest.getExperience());
        EmployeeDto savedEmployee = employeeService.save(employeeDto);
        return CreateEmployeeResponse.builder()
                .age(savedEmployee.getAge())
                .experience(savedEmployee.getExperience())
                .name(savedEmployee.getName())
                .build();
    }

    @GetMapping(value = "/test", produces = "application/json")
    public EmployeeDto test(@RequestParam Map<String, String> params) {
        EmployeeDto byId = null;
        String idParamValue = params.get("id");
        if (!idParamValue.isEmpty()) {
            byId = employeeService.findById(Integer.parseInt(idParamValue));
        }

        return byId;
    }

    @GetMapping("{id}")
    public EmployeeDto getById(@PathVariable int id) {
        return employeeService.findById(id);
    }

    @GetMapping("/name/{name}")
    public List<EmployeeDto> getByName(@PathVariable String name) {
        return employeeService.findByName(name);
    }

    @GetMapping("/criteria/byExperience{experience}")
    public List<EmployeeDto> getByExperience(@PathVariable int experience) {
        return employeeService.getByExperience(experience);
    }

    @GetMapping("/criteria/byAge/{age}")
    public List<EmployeeDto> getByAge(@PathVariable int age) {
        return employeeService.getEmployeeByAge(age);
    }

    @GetMapping("/criteria/names")
    public List<String> getEmployeeNames() {
        return employeeService.getCriteriaNames();
    }

    @GetMapping("/criteriaRestriction/byAge")
    public List<EmployeeDto> getCriteriaRestriction() {
        return employeeService.getCriteriaAge();
    }

    @GetMapping("/spec/names/{name}")
    public List<EmployeeEntity> getNames(@PathVariable String name) {
        return employeeSpecDao.getEmployeeByName(name);
    }

    @GetMapping("/spec/searchOne/{searchOne}")
    public List<EmployeeEntity> searchLikeName(@PathVariable String searchOne) {
        return employeeSpecDao.searchEmployeeLikeName(searchOne);
    }


}
