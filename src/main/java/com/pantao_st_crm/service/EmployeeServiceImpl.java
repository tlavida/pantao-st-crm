package com.pantao_st_crm.service;

import com.pantao_st_crm.dto.EmployeeDTO;
import com.pantao_st_crm.entity.Employee;
import com.pantao_st_crm.entity.RoleModel;
import com.pantao_st_crm.repository.EmployeeRepository;
import com.pantao_st_crm.util.mapper.ToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        // Предполагается, что employeeDTO.getRole() возвращает имя роли (строку)
        String roleName = employeeDTO.getRole(); // Получаем строку с именем роли

        // Ищем роль по имени
        RoleModel role = roleModelService.findByName(roleName); // Передаем строку в findByName

        // Преобразуем DTO в сущность Employee
        Employee employee = Employee.builder()
                .fio(employeeDTO.getFio())
                .role(role) // Привязываем объект RoleModel
                .build();

        Employee savedEmployee = employeeRepository.save(employee);
        return ToDTO.toDTOEmployee(savedEmployee); // Возвращаем DTO после сохранения
    }

    @Override
    public Optional<EmployeeDTO> findById(Long id) {
        return employeeRepository.findById(id).map(ToDTO::toDTOEmployee);
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(ToDTO::toDTOEmployee)
                .collect(Collectors.toList());
    }
}
