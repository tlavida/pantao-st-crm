package com.pantao_st_crm.util.mapper;

import com.pantao_st_crm.dto.EmployeeDTO;
import com.pantao_st_crm.dto.RoleModelDTO;
import com.pantao_st_crm.entity.Employee;
import com.pantao_st_crm.entity.RoleModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDTOTest {

    private RoleModel roleModel;

    @BeforeEach
    void setUp() {
        // Инициализируем объект RoleModel перед каждым тестом
        roleModel = RoleModel.builder()
                .id(1L)
                .name("admin")
                .description("Admin role")
                .build();
    }

    @Test
    void toDTORole() {
        // Вызываем
        // Act: преобразуем RoleModel в RoleModelDTO
        RoleModelDTO roleModelDTO = ToDTO.toDTORole(roleModel);

        // Используем
        // Assert: проверяем, что поля RoleModelDTO соответствуют ожидаемым значениям
        assertAll(
                () -> assertEquals(roleModel.getId(), roleModelDTO.getId()),
                () -> assertEquals(roleModel.getName(), roleModelDTO.getName()),
                () -> assertEquals(roleModel.getDescription(), roleModelDTO.getDescription())
        );
    }

    @Test
    void toDTOEmployee() {
        // Создаем
        // Arrange: создаем тестовый объект Employee
        Employee employee = Employee.builder()
                .id(2L)
                .fio("Петров П. П.")
                .role(roleModel)
                .build();

        // Вызываем
        EmployeeDTO employeeDTO = ToDTO.toDTOEmployee(employee);

        // Используем
        assertAll(
                () -> assertEquals(employee.getId(), employeeDTO.getId()),
                () -> assertEquals(employee.getFio(), employeeDTO.getFio()),
                () -> assertEquals(roleModel.getName(), employeeDTO.getRole())
        );
    }
}