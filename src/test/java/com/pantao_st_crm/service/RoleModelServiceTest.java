package com.pantao_st_crm.service;

import com.pantao_st_crm.dto.RoleModelDTO;
import com.pantao_st_crm.entity.RoleModel;
import com.pantao_st_crm.repository.RoleModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // Говорит Spring, что это интеграционные тесты, загружает контекст приложения
class RoleModelServiceTest {

    @Autowired
    RoleModelRepository roleModelRepository;
    @Autowired
    RoleModelService roleModelService;

    private Long adminRoleId; // Переменная для хранения id роли admin

    @BeforeEach
    public void setUp() {
        // Перед каждым тестом очищаем базу и загружаем тестовые данные
        roleModelRepository.deleteAll();

        // Добавляем тестовые данные в базу
        RoleModel adminRole = RoleModel.builder()
                .name("admin")
                .description("Admin role")
                .build();

        RoleModel operatorRole = RoleModel.builder()
                .name("operator")
                .description("Operator role")
                .build();

        roleModelRepository.save(adminRole);
        adminRoleId = adminRole.getId(); // Сохраняем сгенерированный id

        roleModelRepository.save(operatorRole);
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
    void findById() {
        // Act: вызов метода findById у сервиса
        Optional<RoleModelDTO> roleModelDTO = roleModelService.findById(adminRoleId);

        // Assert: проверяем, что роль с id = 1 найдена и ее поля верны
        assertTrue(roleModelDTO.isPresent());
        assertEquals(adminRoleId, roleModelDTO.get().getId());
        assertEquals("admin", roleModelDTO.get().getName());
        assertEquals("Admin role", roleModelDTO.get().getDescription());
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