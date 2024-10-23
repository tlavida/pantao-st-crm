package com.pantao_st_crm.controller;

import com.pantao_st_crm.dto.EmployeeDTO;
import com.pantao_st_crm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.employeeService = service;
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> create(@RequestBody EmployeeDTO dto) {
        // Сохраняем сотрудника через сервис
        EmployeeDTO createdEmployee = employeeService.save(dto);

        // Возвращаем 201 Created и созданный объект в теле ответа
        return ResponseEntity
                .status(HttpStatus.CREATED) // Устанавливаем статус 201 Created
                .body(createdEmployee); // Возвращаем созданного сотрудника
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> update(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        // Проверяем, существует ли сотрудник с данным ID
        employeeDTO.setId(id);
        EmployeeDTO updatedEmployee = employeeService.update(employeeDTO);

        return ResponseEntity.ok(updatedEmployee);
    }


    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        List<EmployeeDTO> employeeDTOList = employeeService.findAll();

        if (employeeDTOList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employeeDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getById(@PathVariable Long id) {
        EmployeeDTO employeeDTO = employeeService.findById(id);
        return ResponseEntity.ok(employeeDTO);
    }
}
