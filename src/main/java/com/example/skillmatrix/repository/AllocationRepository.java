package com.example.skillmatrix.repository;

import com.example.skillmatrix.entity.Allocation;
import com.example.skillmatrix.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllocationRepository
        extends JpaRepository<Allocation, Long> {

    List<Allocation> findByEmployee(Employee employee);

    List<Allocation> findByProjectId(Long projectId);
}