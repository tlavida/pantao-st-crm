package com.pantao_st_crm.service;

import com.pantao_st_crm.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO save(EmployeeDTO employeeDTO);
    EmployeeDTO update(EmployeeDTO employeeDTO);
    EmployeeDTO findById(Long id);
    List<EmployeeDTO> findAll();
}
