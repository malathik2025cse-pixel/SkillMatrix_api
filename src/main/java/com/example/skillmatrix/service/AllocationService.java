package com.example.skillmatrix.service;

import com.example.skillmatrix.entity.Allocation;
import com.example.skillmatrix.entity.Employee;
import com.example.skillmatrix.entity.Project;
import com.example.skillmatrix.repository.AllocationRepository;
import com.example.skillmatrix.repository.EmployeeRepository;
import com.example.skillmatrix.repository.ProjectRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationService {

    private final AllocationRepository allocationRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    public AllocationService(
            AllocationRepository allocationRepository,
            EmployeeRepository employeeRepository,
            ProjectRepository projectRepository) {

        this.allocationRepository = allocationRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    public Allocation createAllocation(
            Long employeeId,
            Long projectId,
            Integer allocationPercentage) {

        Employee employee =
                employeeRepository.findById(employeeId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Employee not found with id: "
                                                + employeeId));

        Project project =
                projectRepository.findById(projectId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Project not found with id: "
                                                + projectId));

        if (allocationPercentage == null ||
                allocationPercentage < 1 ||
                allocationPercentage > 100) {

            throw new RuntimeException(
                    "Allocation percentage must be between 1 and 100");
        }

        if (allocationPercentage > employee.getAvailability()) {
    throw new RuntimeException(
            "Allocation exceeds employee availability. "
                    + "Available: "
                    + employee.getAvailability()
                    + "%, Requested: "
                    + allocationPercentage
                    + "%"
    );
}
        Allocation allocation =
                new Allocation(
                        employee,
                        project,
                        allocationPercentage
                );

        Allocation savedAllocation =
                allocationRepository.save(allocation);

        updateEmployeeAvailability(employee);

        return savedAllocation;
    }

    public List<Allocation> getAllAllocations() {

        return allocationRepository.findAll();
    }

    public Allocation getAllocationById(Long id) {

        return allocationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Allocation not found with id: " + id
                        ));
    }

    public List<Allocation> getAllocationsByProject(
            Long projectId) {

        return allocationRepository
                .findByProjectId(projectId);
    }

    public void deleteAllocation(Long id) {

        Allocation allocation =
                allocationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Allocation not found with id: "
                                                + id
                                ));

        Employee employee = allocation.getEmployee();

        allocationRepository.delete(allocation);

        updateEmployeeAvailability(employee);
    }

    private void updateEmployeeAvailability(
            Employee employee) {

        int currentAllocation =
                allocationRepository
                        .findByEmployee(employee)
                        .stream()
                        .mapToInt(Allocation::getAllocationPercentage)
                        .sum();

        int availability =
                employee.getTotalCapacity()
                        - currentAllocation;

        employee.setAvailability(
                Math.max(availability, 0)
        );

        employeeRepository.save(employee);
    }
}