package com.example.skillmatrix.service;

import com.example.skillmatrix.entity.Employee;
import com.example.skillmatrix.repository.EmployeeRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Employee not found with id: " + id
                        ));
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {

        Employee existingEmployee =
                employeeRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Employee not found with id: " + id
                                ));

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setEmail(updatedEmployee.getEmail());

        existingEmployee.setTotalCapacity(
        updatedEmployee.getTotalCapacity()
        );

         existingEmployee.setAvailability(
        updatedEmployee.getAvailability()
        );
        existingEmployee.setSkillProficiencies(
        updatedEmployee.getSkillProficiencies()
        );
        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {

        Employee employee =
                employeeRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Employee not found with id: " + id
                                ));

        employeeRepository.delete(employee);
    }
}