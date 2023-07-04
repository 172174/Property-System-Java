package com.xinyiran.property.mapper.sys;

import com.xinyiran.property.entity.dto.RoleMenuDto;
import com.xinyiran.property.entity.sys.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    List<String> getRolesByUserId(Long userId);

    List<Role> selectRoleList();

    List<Role> selectRoleListDetails();

    Role selectRoleInfoByRoleId(Long roleId);

    void deleteRoleMenu(Long roleId);

    void authorizationBatch(List<RoleMenuDto> roleMenuDtoList);

    Long setRole(Long userId,Long roleId);
}
