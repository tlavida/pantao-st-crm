package com.pantao_st_crm.controller;

import com.pantao_st_crm.entity.RoleModel;
import com.pantao_st_crm.service.RoleModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role_model")
public class RoleModelController {
    private final RoleModelServiceImpl service;

    @Autowired
    public RoleModelController(RoleModelServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<RoleModel> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<RoleModel> getById(@PathVariable Long id) {
        return service.findById(id);
    }
}
