package com.pantao_st_crm.service;

import com.pantao_st_crm.dto.EmployeeDTO;
import com.pantao_st_crm.entity.Employee;
import com.pantao_st_crm.entity.RoleModel;
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

        RoleModel roleModel = RoleModel.builder()
                .name("admin")
                .description("Admin role")
                .build();

        Employee employee = Employee.builder()
                .fio("Петров П. П.")
                .role(roleModel)
                .build();

        roleModelRepository.save(roleModel);
        employeeRepository.save(employee);

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
        Optional<EmployeeDTO> employeeDTO = employeeService.findById(employeeId);

        assertTrue(employeeDTO.isPresent());
        assertEquals(employeeId, employeeDTO.get().getId());
        assertEquals("Петров П. П.", employeeDTO.get().getFio());
        assertEquals("admin", employeeDTO.get().getRole());
    }

    @Test
    void findById_empty() {
        // Ищем несуществующего сотрудника с id = 900L
        Optional<EmployeeDTO> employeeDTO = employeeService.findById(900L);

        // Проверяем, что сотрудник не найден
        assertFalse(employeeDTO.isPresent());
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
}