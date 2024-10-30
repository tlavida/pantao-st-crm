package com.pantao_st_crm.util.mapper;

import com.pantao_st_crm.dto.EmployeeDTO;
import com.pantao_st_crm.dto.RoleModelDTO;
import com.pantao_st_crm.entity.Employee;
import com.pantao_st_crm.entity.RoleModel;

public class ToDTO {
    public static RoleModelDTO toDTORole(RoleModel entity) {
        return RoleModelDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    public static EmployeeDTO toDTOEmployee(Employee entity) {
        return EmployeeDTO.builder()
                .id(entity.getId())
                .fio(entity.getFio())
                .role(entity.getRole().getName())
                .login(entity.getLogin())
                .build();
    }
}
