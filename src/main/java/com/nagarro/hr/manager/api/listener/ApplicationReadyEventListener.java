package com.nagarro.hr.manager.api.listener;

import java.time.LocalDate;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.nagarro.hr.manager.api.model.Employee;
import com.nagarro.hr.manager.api.model.HrManager;
import com.nagarro.hr.manager.api.repository.EmployeeRepository;
import com.nagarro.hr.manager.api.repository.HrManagerRepository;

@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

	private final HrManagerRepository hrManagerRepository;
	private final EmployeeRepository employeeRepository;

	public ApplicationReadyEventListener(HrManagerRepository hrManagerRepository,
			EmployeeRepository employeeRepository) {
		this.hrManagerRepository = hrManagerRepository;
		this.employeeRepository = employeeRepository;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
//		setUp(hrManagerRepository);
		System.out.println("started REST");

	}

	private static void setUp(HrManagerRepository hrManagerRepository) {
		Employee employee1 = new Employee();
		employee1.setCode(1);
		employee1.setName("Employee1");
		employee1.setEmail("abc@gmail.com");
		employee1.setLocation("jalandhar");
		employee1.setDateOfBirth(LocalDate.parse("2020-01-01"));

		Employee employee2 = new Employee();
		employee2.setCode(2);
		employee2.setName("Employee2");
		employee2.setEmail("def@gmail.com");
		employee2.setLocation("ludhiana");
		employee2.setDateOfBirth(LocalDate.parse("2020-02-02"));

		Employee employee3 = new Employee();
		employee3.setName("Employee3");
		employee3.setCode(3);
		employee3.setEmail("ijk@gmail.com");
		employee3.setLocation("amritsar");
		employee3.setDateOfBirth(LocalDate.parse("2020-03-03"));

		HrManager manager1 = new HrManager();
		manager1.setName("manager1");

		HrManager manager2 = new HrManager();
		manager2.setName("manager2");

		HrManager manager3 = new HrManager();
		manager3.setName("manager3");

		manager1.getEmployees().add(employee1);
		manager1.getEmployees().add(employee2);
		manager2.getEmployees().add(employee3);

		hrManagerRepository.save(manager1);
		hrManagerRepository.save(manager2);
		hrManagerRepository.save(manager3);
	}

}
