package com.pantao_st_crm.service;

import com.pantao_st_crm.dto.EmployeeDTO;
import com.pantao_st_crm.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeDTO save(EmployeeDTO employeeDTO);
    Optional<EmployeeDTO> findById(Long id);
    List<EmployeeDTO> findAll();
}
