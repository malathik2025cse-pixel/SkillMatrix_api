package com.example.skillmatrix.controller;

import com.example.skillmatrix.entity.Allocation;
import com.example.skillmatrix.service.AllocationService;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allocations")
@Validated
public class AllocationController {

    private final AllocationService allocationService;

    public AllocationController(
            AllocationService allocationService) {

        this.allocationService = allocationService;
    }

    @PostMapping
    public ResponseEntity<Allocation> createAllocation(
            @RequestParam
            @NotNull(message = "Employee ID is required")
            Long employeeId,

            @RequestParam
            @NotNull(message = "Project ID is required")
            Long projectId,

            @RequestParam
            @NotNull(message = "Allocation percentage is required")
            @Min(value = 1,
                    message = "Allocation must be at least 1%")
            @Max(value = 100,
                    message = "Allocation cannot exceed 100%")
            Integer allocationPercentage) {

        Allocation allocation =
                allocationService.createAllocation(
                        employeeId,
                        projectId,
                        allocationPercentage
                );

        return new ResponseEntity<>(
                allocation,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Allocation>> getAllAllocations() {

        return ResponseEntity.ok(
                allocationService.getAllAllocations()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Allocation> getAllocationById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                allocationService.getAllocationById(id)
        );
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Allocation>>
    getAllocationsByProject(
            @PathVariable Long projectId) {

        return ResponseEntity.ok(
                allocationService
                        .getAllocationsByProject(projectId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAllocation(
            @PathVariable Long id) {

        allocationService.deleteAllocation(id);

        return ResponseEntity.ok(
                "Allocation deleted successfully"
        );
    }
}