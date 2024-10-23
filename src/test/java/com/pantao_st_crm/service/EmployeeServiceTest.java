package com.pantao_st_crm.service;

import com.pantao_st_crm.dto.EmployeeDTO;
import com.pantao_st_crm.entity.Employee;
import com.pantao_st_crm.entity.RoleModel;
import com.pantao_st_crm.exception.CustomEntityNotFoundException;
import com.pantao_st_crm.exception.EmployeeAlreadyExistsException;
import com.pantao_st_crm.repository.EmployeeRepository;
import com.pantao_st_crm.repository.RoleModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RoleModelRepository roleModelRepository;

    private Long employeeId;

    @BeforeEach
    public void setUp() {
        employeeRepository.deleteAll();
        roleModelRepository.deleteAll();

        // Сохраняем роль в базу данных
        RoleModel roleModel = RoleModel.builder()
                .name("admin")
                .description("Admin role")
                .build();

        roleModel = roleModelRepository.save(roleModel); // Сохраняем и получаем обновлённую сущность

        // Сохраняем сотрудника с только что сохранённой ролью
        Employee employee = Employee.builder()
                .fio("Петров П. П.")
                .role(roleModel)
                .build();

        employee = employeeRepository.save(employee); // Сохраняем и обновляем сущность

        // Сохраняем ID сотрудника для использования в тестах
        employeeId = employee.getId();
    }

    @Test
    void save() {
        // Сначала создаем новую роль и сохраняем ее
        RoleModel newRole = RoleModel.builder()
                .name("operator")
                .description("Operator role")
                .build();
        newRole = roleModelRepository.save(newRole);

        // Создаем EmployeeDTO, который будет передан в сервис
        EmployeeDTO newEmployeeDTO = EmployeeDTO.builder()
                .fio("Васильев В. В.")
                .role(newRole.getName()) // Передаем имя роли, как ожидается в EmployeeDTO
                .build();

        // Сохраняем сотрудника через сервис
        employeeService.save(newEmployeeDTO);

        // Проверяем, что количество сотрудников в базе данных увеличилось до 2
        assertEquals(2, employeeRepository.count());
    }

    @Test
    void findById() {
        EmployeeDTO employeeDTO = employeeService.findById(employeeId);

        assertNotNull(employeeDTO);
        assertEquals(employeeId, employeeDTO.getId());
        assertEquals("Петров П. П.", employeeDTO.getFio());
        assertEquals("admin", employeeDTO.getRole());
    }

    @Test
    void findById_empty() {
        Long nonExistentId = 999L;

        assertThrows(CustomEntityNotFoundException.class, () -> employeeService.findById(nonExistentId));
    }

    @Test
    void findAll() {
        List<EmployeeDTO> employeeDTOS = employeeService.findAll();
        assertEquals(1, employeeDTOS.size());
    }

    @Test
    void findAll_empty() {
        employeeRepository.deleteAll();

        List<EmployeeDTO> employeeDTOS = employeeService.findAll();
        assertEquals(0, employeeDTOS.size());
    }

    @Test
    void save_shouldThrowExceptionWhenEmployeeAlreadyExists() {
        EmployeeDTO duplicateEmployeeDTO = EmployeeDTO.builder()
                .fio("Петров П. П.") // Такое же имя, как у уже существующего сотрудника
                .role("admin")
                .build();

        assertThrows(EmployeeAlreadyExistsException.class, () -> employeeService.save(duplicateEmployeeDTO));
    }

    @Test
    void update() {
        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .id(employeeId)
                .fio("Обновленное ФИО Сотрудника")
                .role("admin")
                .build();

        EmployeeDTO updatedEmployee = employeeService.update(employeeDTO);

        assertNotNull(updatedEmployee);
        assertEquals("Обновленное ФИО Сотрудника", updatedEmployee.getFio());
    }

    @Test
    void update_shouldThrowExceptionWhenEmployeeNotFound() {
        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .id(999L) // Несуществующий ID
                .fio("Сотрудник не найден")
                .role("admin")
                .build();

        assertThrows(CustomEntityNotFoundException.class, () -> employeeService.update(employeeDTO));
    }
}