package com.nagarro.hr.manager.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.hr.manager.api.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
