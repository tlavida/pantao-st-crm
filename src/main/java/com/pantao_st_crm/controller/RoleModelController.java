package com.pantao_st_crm.controller;

import com.pantao_st_crm.dto.RoleModelDTO;
import com.pantao_st_crm.service.Impl.RoleModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleModelController {
    private final RoleModelServiceImpl service;

    @Autowired
    public RoleModelController(RoleModelServiceImpl service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<RoleModelDTO>> getAll() {
        List<RoleModelDTO> roleModelDTOList = service.findAll();

        // Возвращаем список ролей и статус 200 OK, если список не пустой
        if (roleModelDTOList.isEmpty()) {
            return ResponseEntity.noContent().build();  // Если пусто, возвращаем статус 204 No Content
        }
        return ResponseEntity.ok(roleModelDTOList); // Возвращаем статус 200 OK и список
    }
}
