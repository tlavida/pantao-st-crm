package com.pantao_st_crm.repository;


import com.pantao_st_crm.entity.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleModelRepository extends JpaRepository<RoleModel, Long> {
    RoleModel findByName(String name);
}
