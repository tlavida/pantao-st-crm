package com.pantao_st_crm.service.Impl;

import com.pantao_st_crm.dto.EmployeeDTO;
import com.pantao_st_crm.entity.Employee;
import com.pantao_st_crm.entity.RoleModel;
import com.pantao_st_crm.exception.CustomEntityNotFoundException;
import com.pantao_st_crm.exception.EmployeeAlreadyExistsException;
import com.pantao_st_crm.repository.EmployeeRepository;
import com.pantao_st_crm.service.EmployeeService;
import com.pantao_st_crm.service.RoleModelService;
import com.pantao_st_crm.util.mapper.ToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Transactional
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        // Проверяем, существует ли уже сотрудник с таким же именем (fio)
        if (employeeRepository.existsByFio(employeeDTO.getFio())) {
            throw new EmployeeAlreadyExistsException(Employee.class);
        }

        // Предполагается, что employeeDTO.getRole() возвращает имя роли (строку)
        String roleName = employeeDTO.getRole(); // Получаем строку с именем роли

        // Ищем роль по имени
        RoleModel role = roleModelService.findByName(roleName);
        if (role == null) {
            throw new CustomEntityNotFoundException(RoleModel.class);
        }

        // Преобразуем DTO в сущность Employee
        Employee employee = Employee.builder()
                .fio(employeeDTO.getFio())
                .role(role) // Привязываем объект RoleModel
                .build();

        Employee savedEmployee = employeeRepository.save(employee);
        return ToDTO.toDTOEmployee(savedEmployee); // Возвращаем DTO после сохранения
    }

    @Override
    @Transactional
    public EmployeeDTO update(EmployeeDTO employeeDTO) {
        // Ищем сотрудника по ID
        Employee employee = employeeRepository.findById(employeeDTO.getId())
                .orElseThrow(() -> new CustomEntityNotFoundException(Employee.class));

        // Обновляем ФИО сотрудника
        employee.setFio(employeeDTO.getFio());

        // Ищем роль через сервисный слой
        RoleModel roleModel = roleModelService.findByName(employeeDTO.getRole());

        // Обновляем роль сотрудника
        employee.setRole(roleModel);

        // Сохраняем обновленного сотрудника
        Employee updatedEmployee = employeeRepository.save(employee);

        // Преобразуем сущность в DTO и возвращаем результат
        return ToDTO.toDTOEmployee(updatedEmployee);
    }


    @Override
    @Transactional
    public EmployeeDTO findById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new CustomEntityNotFoundException(Employee.class));
        return ToDTO.toDTOEmployee(employee);
    }

    @Override
    @Transactional
    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(ToDTO::toDTOEmployee)
                .collect(Collectors.toList());
    }
}
