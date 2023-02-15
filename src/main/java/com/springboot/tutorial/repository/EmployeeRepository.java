package com.springboot.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.tutorial.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
