package com.pantao_st_crm.service;

import com.pantao_st_crm.dto.RoleModelDTO;
import com.pantao_st_crm.entity.RoleModel;

import java.util.List;
import java.util.Optional;

public interface RoleModelService {
    RoleModel findByName(String name);
    Optional<RoleModelDTO> findById(Long id);
    List<RoleModelDTO> findAll();
}
