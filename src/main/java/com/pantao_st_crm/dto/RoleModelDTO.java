package com.pantao_st_crm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoleModelDTO {
    @JsonProperty("roleId")
    private Long id;
    @JsonProperty("roleName")
    private String name;
    @JsonProperty("roleDescription")
    private String description;
}