package com.example.demo.model;

import org.springframework.data.jpa.domain.Specification;

public final class EmployeeEntitySpec {
    public static Specification<EmployeeEntity> getByName(String name){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("name"), name);
        });
    }
    public static Specification<EmployeeEntity> searchLikeName(String searchOne){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), searchOne);


        });
    }
}
