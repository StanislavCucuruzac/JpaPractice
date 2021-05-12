package com.example.demo.repository;

import com.example.demo.model.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

import static com.example.demo.model.EmployeeEntitySpec.*;

@Repository
public class EmployeeDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public EmployeeEntity save(EmployeeEntity employeeEntity) {
        entityManager.persist(employeeEntity);
        return employeeEntity;
    }

    public EmployeeEntity findById(int id) {
        return entityManager.find(EmployeeEntity.class, id);
    }

    public List<EmployeeEntity> findByName(String name) {
       Query query= entityManager.createNativeQuery("select * from employee where name=:name", EmployeeEntity.class);
       query.setParameter("name", name);
       return query.getResultList();
    }

    public List<EmployeeEntity> findAllSQL() {
        return entityManager.createNativeQuery("select * from employee", EmployeeEntity.class)
                .getResultList();
    }

    public List<EmployeeEntity> getEmployeeByExperienceWithNamedQuery(int experience) {
        Query namedQuery = entityManager.createNamedQuery("employeeEntity.findByExperience");
        namedQuery.setParameter("experience", experience);
        return namedQuery.getResultList();
    }

    public List<EmployeeEntity> getEmployeeByAgeWithNamedQuery(int age) {
        Query namedQuery = entityManager.createNamedQuery("employeeEntity.findByAge");
        namedQuery.setParameter("age", age);
        return namedQuery.getResultList();
    }

    public List<String> getCriteriaEmployeeNames() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder(); // criteria builder create criteria query
        CriteriaQuery<String> criteriaQuery = cb.createQuery(String.class); // result of choose
        Root<EmployeeEntity> employeeRoot = criteriaQuery.from(EmployeeEntity.class); // the all objects Employee.class
        criteriaQuery.select(employeeRoot.get("name"));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<EmployeeEntity> getCriteriaAge() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<EmployeeEntity> criteriaQuery = cb.createQuery(EmployeeEntity.class);
        Root<EmployeeEntity> c = criteriaQuery.from(EmployeeEntity.class);
        ParameterExpression<Integer> p = cb.parameter(Integer.class);

//        mapul.forEach((k,v) -> cb.gt(c.get(k), v))
        criteriaQuery.select(c).where(cb.gt(c.get("age"), p));

        TypedQuery<EmployeeEntity> query = entityManager.createQuery(criteriaQuery);
        query.setParameter(p, 19);
        List<EmployeeEntity> results = query.getResultList();
        return results;
    }


}
