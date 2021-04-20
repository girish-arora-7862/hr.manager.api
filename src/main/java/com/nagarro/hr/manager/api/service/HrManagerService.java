package com.nagarro.hr.manager.api.service;

import java.util.List;

import com.nagarro.hr.manager.api.model.Employee;
import com.nagarro.hr.manager.api.model.HrManager;

public interface HrManagerService {
	List<Employee> getAllEmployeesByHRManagerName(String name);

	HrManager getHrManagerByName(String name);

	Employee getEmloyeeByNameAndCode(String hrName, long code);

	Employee updatEmployeeByNameAndCode(String hrName, long employeeCode, Employee newEmployee);

	List<Employee> saveEmployeesForHrManager(String hrName, List<Employee> employees);

}