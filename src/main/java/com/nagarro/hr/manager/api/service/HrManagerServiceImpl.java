package com.nagarro.hr.manager.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.nagarro.hr.manager.api.model.Employee;
import com.nagarro.hr.manager.api.model.HrManager;
import com.nagarro.hr.manager.api.repository.EmployeeRepository;
import com.nagarro.hr.manager.api.repository.HrManagerRepository;

@Service
public class HrManagerServiceImpl implements HrManagerService {

	private final HrManagerRepository hrManagerRepository;
	private final EmployeeRepository employeeRepository;

	public HrManagerServiceImpl(HrManagerRepository hrManagerRepository, EmployeeRepository employeeRepository) {
		this.hrManagerRepository = hrManagerRepository;
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> getAllEmployeesByHRManagerName(String name) {
		HrManager hrManager = hrManagerRepository.getHrManagerByName(name);
		if (Objects.isNull(hrManager)) {
			return new ArrayList<>();
		}
		return hrManager.getEmployees();
	}

	@Override
	public HrManager getHrManagerByName(String name) {
		return hrManagerRepository.getHrManagerByName(name);
	}

	@Override
	public Employee getEmloyeeByNameAndCode(String hrName, long employeeCode) {
		return hrManagerRepository.getEmloyeeByNameAndCode(hrName, employeeCode);
	}

	@Override
	public Employee updatEmployeeByNameAndCode(String hrName, long employeeCode, Employee newEmployee) {
		Employee oldEmployee = hrManagerRepository.getEmloyeeByNameAndCode(hrName, employeeCode);
		if (Objects.isNull(oldEmployee)) {
			return null;
		}
		BeanUtils.copyProperties(newEmployee, oldEmployee, "code");
		Employee savedEmployee = employeeRepository.save(oldEmployee);
		return savedEmployee;
	}

	@Override
	public List<Employee> saveEmployeesForHrManager(String hrName, List<Employee> newEmployees) {
		HrManager hrManager = hrManagerRepository.getHrManagerByName(hrName);
		if (Objects.isNull(hrManager)) {
			return null;
		}
		Map<Long,Employee> map = Stream.concat(hrManager.getEmployees().stream(), newEmployees.stream())
				.collect(Collectors.toMap(Employee::getCode, Employee::fetchThisObject, (oldValue,newValue) -> newValue));
		List<Employee> uniquEmployees = map.values().stream().collect(Collectors.toList());
		hrManager.setEmployees(uniquEmployees);
		hrManagerRepository.save(hrManager);
		return newEmployees;
	}

}
