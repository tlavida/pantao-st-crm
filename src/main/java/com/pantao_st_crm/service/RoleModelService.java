package com.pantao_st_crm.service;

import com.pantao_st_crm.dto.RoleModelDTO;
import com.pantao_st_crm.entity.RoleModel;

import java.util.List;

public interface RoleModelService {
    RoleModel findByName(String name);
    List<RoleModelDTO> findAll();
}
