package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "employee")
@NamedQueries(
        {@NamedQuery(name = "employeeEntity.findByExperience", query = "SELECT e FROM EmployeeEntity e WHERE e.experience=:experience"),
            @NamedQuery(name = "employeeEntity.findByAge", query = "SELECT e FROM EmployeeEntity e WHERE e.age=:age")
        })
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(notes = "Name of the Employee",name="name",required=true,value="test name")
    private String name;
    private int age;
    private int experience;
}
