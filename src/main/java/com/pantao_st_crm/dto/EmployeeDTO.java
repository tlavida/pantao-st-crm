package com.pantao_st_crm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pantao_st_crm.entity.RoleModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmployeeDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("fio")
    private String fio;
    @JsonProperty("role")
    private String role;
}
