package com.nagarro.hr.manager.api.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.hr.manager.api.exception.MyException;
import com.nagarro.hr.manager.api.model.Employee;
import com.nagarro.hr.manager.api.service.HrManagerService;

@RestController
public class EmployeeController {

	private final HrManagerService hrManagerService;

	public EmployeeController(HrManagerService hrManagerService) {
		this.hrManagerService = hrManagerService;
	}

	@RequestMapping(value = "/hr/{name}/employee", method = RequestMethod.GET)
	public List<Employee> getAllEmployees(@PathVariable String name) throws MyException {
		List<Employee> employees = hrManagerService.getAllEmployeesByHRManagerName(name);
		if (Objects.isNull(employees) || employees.isEmpty()) {
			throw new MyException("No Employee Found With HrManager Name : " + name);
		}
		return employees;
	}

	@RequestMapping(value = "/hr/{name}/employee/{code}", method = RequestMethod.GET)
	public Employee getOneEmployee(@PathVariable String name, @PathVariable long code) throws MyException {
		Employee employee = hrManagerService.getEmloyeeByNameAndCode(name, code);
		if (Objects.isNull(employee)) {
			throw new MyException("No Employee Found With HrManager Name : " + name + " , Employee Code : " + code);
		}
		return employee;

	}

	@RequestMapping(value = "/hr/{name}/employee/{code}", method = RequestMethod.PUT)
	public Employee updateEmployee(@PathVariable String name, @PathVariable long code, @RequestBody Employee employee)
			throws MyException {
		Employee updatedEmployee = hrManagerService.updatEmployeeByNameAndCode(name, code, employee);
		if (Objects.isNull(updatedEmployee)) {
			throw new MyException(
					"Failed To Update. No Employee Found With HrManager Name : " + name + " , Employee Code : " + code);
		}
		return updatedEmployee;
	}

	@RequestMapping(value = "/hr/{name}/employees", method = RequestMethod.POST)
	public List<Employee> postEmployees(@PathVariable String name, @RequestBody List<Employee> employees)
			throws MyException {
		List<Employee> savedEmployees = hrManagerService.saveEmployeesForHrManager(name, employees);
		if (Objects.isNull(savedEmployees)) {
			throw new MyException("Failed To Save. No HrManager Found With HrManager Name : " + name);
		}
		return employees;
	}

}
