package com.pantao_st_crm.service;

import com.pantao_st_crm.entity.RoleModel;
import com.pantao_st_crm.repository.RoleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleModelServiceImpl implements RoleModelService{
    private final RoleModelRepository repository;

    @Autowired
    public RoleModelServiceImpl(RoleModelRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<RoleModel> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<RoleModel> findAll() {
        return repository.findAll();
    }
}
