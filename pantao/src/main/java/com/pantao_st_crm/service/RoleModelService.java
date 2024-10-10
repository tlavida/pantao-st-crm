package com.pantao_st_crm.service;

import com.pantao_st_crm.entity.RoleModel;

import java.util.List;
import java.util.Optional;

public interface RoleModelService {
    Optional<RoleModel> findById(Long id);
    List<RoleModel> findAll();
}
