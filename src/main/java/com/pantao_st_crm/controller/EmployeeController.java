package com.pantao_st_crm.controller;

import com.pantao_st_crm.dto.EmployeeDTO;
import com.pantao_st_crm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.employeeService = service;
    }

    @PostMapping
    public EmployeeDTO create(@RequestBody EmployeeDTO dto) {
        return employeeService.save(dto);
    }

    @GetMapping
    public List<EmployeeDTO> getAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<EmployeeDTO> getById(@PathVariable Long id) {
        return employeeService.findById(id);
    }
}
