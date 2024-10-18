package com.pantao_st_crm.service;

import com.pantao_st_crm.dto.EmployeeDTO;
import com.pantao_st_crm.entity.Employee;

public interface EmployeeService {
    EmployeeDTO save(EmployeeDTO employeeDTO);
}
