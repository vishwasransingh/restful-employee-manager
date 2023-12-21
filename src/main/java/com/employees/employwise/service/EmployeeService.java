package com.employees.employwise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.employees.employwise.bean.Employee;
import com.employees.employwise.repository.EmployeeRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;

@Service
@Validated
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private MailService mailService;
	
	@PostConstruct
	public void init() {
		employeeRepository.save(new Employee(0, "Vishwas", "7743898263", "vishwasransingh1@gmail.com", 1, "https://images.pexels.com/photos/614810/pexels-photo-614810.jpeg"));
		employeeRepository.save(new Employee(0, "Abhi", "78618427", "abhi@example.com", 1, "https://images.pexels.com/photos/614810/pexels-photo-614810.jpeg"));
		employeeRepository.save(new Employee(0, "Bipin", "782382323", "bips@example.com", 2, "https://images.pexels.com/photos/614810/pexels-photo-614810.jpeg"));
		employeeRepository.save(new Employee(0, "Chatur", "1234566", "chat@example.com", 2, "https://images.pexels.com/photos/614810/pexels-photo-614810.jpeg"));
		employeeRepository.save(new Employee(0, "Diga", "6625384", "diga@example.com", 2, "https://images.pexels.com/photos/614810/pexels-photo-614810.jpeg"));
	}

	public List<Employee> getAllEmlpoyees() {
		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(int id) {
		return employeeRepository.findById(id).orElse(null);
	}

	public int createEmployee(Employee employee) {
		employeeRepository.save(employee);
        String level1ManagerEmail = getLevel1ManagerEmail(employee.getReportsTo());

        if (level1ManagerEmail != null) {
            String emailSubject = "New Employee Addition";
            String emailText = String.format("%s will now work under you. Mobile number is %s and email is %s",
                    employee.getEmployeeName(), employee.getPhoneNumber(), employee.getEmail());

            mailService.sendEmail(level1ManagerEmail, emailSubject, emailText);
        }
		return employee.getId();
	}
	
	private String getLevel1ManagerEmail(int employeeId) {
        // Traverse the reporting hierarchy to find the level 1 manager email
        Employee currentEmployee = employeeRepository.findById(employeeId).orElse(null);

        if (currentEmployee != null) {
            int reportsTo = currentEmployee.getReportsTo();

            if (reportsTo != 0) {
                Employee level1Manager = employeeRepository.findById(reportsTo).orElse(null);

                if (level1Manager != null) {
                    return level1Manager.getEmail();
                }
            }
        }

        return null; // Return null if level 1 manager email is not found
    }

	public void updateEmployee(int id, Employee updatedEmployee) {
		
		Employee employee = employeeRepository.findById(id).get();
		if (!employee.equals(null)) {
			employee.setId(id);
			employee.setEmployeeName(updatedEmployee.getEmployeeName());
			employee.setEmail(updatedEmployee.getEmail());
			employee.setPhoneNumber(updatedEmployee.getPhoneNumber());
			employee.setProfileImage(updatedEmployee.getProfileImage());
			employee.setReportsTo(updatedEmployee.getReportsTo());
			employeeRepository.save(employee);
		}

	}

	public void deleteEmployee(int id) {
		
		Employee employee = employeeRepository.findById(id).orElse(null);
		
		if (!employee.equals(null)) {
			employeeRepository.delete(employee);
		}
		
	}
	
	public Employee getNthLevelManager(int employeeId, int level) {
        Employee employee = getEmployeeById(employeeId);
        if (employee == null) {
            throw new EntityNotFoundException("Employee not found with ID: " + employeeId);
        }

        if (level <= 0) {
            throw new IllegalArgumentException("Invalid level: " + level);
        }

        return findNthLevelManager(employee, level);
    }

    private Employee findNthLevelManager(Employee employee, int level) {
        while (level > 0 && employee != null) {
            employee = getEmployeeById(employee.getReportsTo());
            level--;
        }
        return employee;
        
    }
    
    public Page<Employee> getAllEmployeesWithPaginationAndSorting(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return employeeRepository.findAll(pageable);
    }
	
}
