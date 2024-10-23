package com.pantao_st_crm.service;

import com.pantao_st_crm.dto.RoleModelDTO;
import com.pantao_st_crm.entity.RoleModel;
import com.pantao_st_crm.exception.CustomEntityNotFoundException;
import com.pantao_st_crm.repository.RoleModelRepository;
import com.pantao_st_crm.util.mapper.ToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleModelServiceImpl implements RoleModelService{
    private final RoleModelRepository roleModelRepository;

    @Autowired
    public RoleModelServiceImpl(RoleModelRepository repository) {
        this.roleModelRepository = repository;
    }

    @Override
    public RoleModel findByName(String name) {
        RoleModel role = roleModelRepository.findByName(name);
        if (role == null) {
            throw new CustomEntityNotFoundException(RoleModel.class);
        }
        return role;
    }

    @Override
    public List<RoleModelDTO> findAll() {
        return roleModelRepository.findAll().stream()
                .map(ToDTO::toDTORole)
                .collect(Collectors.toList());
    }
}
