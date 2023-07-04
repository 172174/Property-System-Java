package com.xinyiran.property.entity.dto;

import lombok.Data;

@Data
public class RoleMenuDto {
    private Long roleId;
    private Long menuId;

    public RoleMenuDto(Long roleId, Long menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }

    public RoleMenuDto() {
    }
}
