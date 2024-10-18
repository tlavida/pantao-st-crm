package com.pantao_st_crm.service;

import com.pantao_st_crm.dto.EmployeeDTO;
import com.pantao_st_crm.entity.Employee;
import com.pantao_st_crm.repository.EmployeeRepository;
import com.pantao_st_crm.util.mapper.ToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final RoleModelService roleModelService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, RoleModelService roleModelService) {
        this.employeeRepository = employeeRepository;
        this.roleModelService = roleModelService;
    }

    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = Employee.builder()
                .fio(employeeDTO.getFio())
                // Чтобы не создавать новую роль, а привязать к существующей в таблице role_model
                .role(roleModelService.findByName(employeeDTO.getRole()))
                .build();
        Employee saveEmployee = employeeRepository.save(employee);
        return ToDTO.toDTOEmployee(saveEmployee);
    }
}
