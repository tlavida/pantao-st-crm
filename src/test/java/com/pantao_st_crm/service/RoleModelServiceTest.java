package com.pantao_st_crm.service;

import com.pantao_st_crm.dto.RoleModelDTO;
import com.pantao_st_crm.entity.RoleModel;
import com.pantao_st_crm.exception.CustomEntityNotFoundException;
import com.pantao_st_crm.repository.EmployeeRepository;
import com.pantao_st_crm.repository.RoleModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // Говорит Spring, что это интеграционные тесты, загружает контекст приложения
@Transactional
class RoleModelServiceTest {

    @Autowired
    RoleModelRepository roleModelRepository;
    @Autowired
    RoleModelService roleModelService;

    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() {
        // Очистка базы данных перед каждым тестом
        employeeRepository.deleteAll();
        roleModelRepository.deleteAll();

        // Объявляем переменные adminRole и operatorRole
        RoleModel adminRole = null;
        RoleModel operatorRole = null;

        // Проверяем, существуют ли роли, прежде чем добавлять
        if (!roleModelRepository.existsByName("admin")) {
            adminRole = RoleModel.builder()
                    .name("admin")
                    .description("Admin role")
                    .build();
            roleModelRepository.save(adminRole);
        }

        if (!roleModelRepository.existsByName("operator")) {
            operatorRole = RoleModel.builder()
                    .name("operator")
                    .description("Operator role")
                    .build();
            roleModelRepository.save(operatorRole);
        }
    }

    @Test
    void findByName() {
        // Act: проверяем поиск роли по имени
        RoleModel role = roleModelService.findByName("admin");

        // Assert: проверяем поля найденной роли
        assertNotNull(role);
        assertEquals("admin", role.getName());
        assertEquals("Admin role", role.getDescription());
    }

    @Test
    void findByName_empty() {
        assertThrows(CustomEntityNotFoundException.class, () -> roleModelService.findByName("nonexistent"));
    }

    @Test
    void findAll() {
        // Act: получаем все роли
        List<RoleModelDTO> roles = roleModelService.findAll();

        // Assert: проверяем, что роли найдены
        assertNotNull(roles);
        assertEquals(2, roles.size());
    }

    @Test
    void findAll_empty() {
        // Очищаем базу данных перед тестом
        roleModelRepository.deleteAll();

        List<RoleModelDTO> roles = roleModelService.findAll();
        assertEquals(0, roles.size());
    }
}