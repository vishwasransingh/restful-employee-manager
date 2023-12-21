package com.employees.employwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employees.employwise.bean.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
