package com.pantao_st_crm.service;

import com.pantao_st_crm.dto.RoleModelDTO;
import com.pantao_st_crm.entity.RoleModel;
import com.pantao_st_crm.repository.RoleModelRepository;
import com.pantao_st_crm.util.mapper.ToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleModelServiceImpl implements RoleModelService{
    private final RoleModelRepository repository;

    @Autowired
    public RoleModelServiceImpl(RoleModelRepository repository) {
        this.repository = repository;
    }

    @Override
    public RoleModel findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Optional<RoleModelDTO> findById(Long id) {
        return repository.findById(id).map(ToDTO::toDTORole);
    }

    @Override
    public List<RoleModelDTO> findAll() {
        return repository.findAll().stream()
                .map(ToDTO::toDTORole)
                .collect(Collectors.toList());
    }
}
