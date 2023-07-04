package com.xinyiran.property.service.sys.impl;


import com.xinyiran.property.entity.dto.RoleMenuDto;
import com.xinyiran.property.entity.sys.Role;
import com.xinyiran.property.mapper.sys.RoleMapper;
import com.xinyiran.property.service.sys.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xinyiran
 * @since 2022-05-08
 */
@Service
public class RoleServiceImp  implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> listRolesByUserId(Long userId) {
        return null;
    }

    /**
     * 授权
     * @param roleId
     * @param menuIds
     * @return
     */
    @Transactional
    @Override
    public Boolean authorized(Long roleId, Long[] menuIds) {
        roleMapper.deleteRoleMenu(roleId);
        List<RoleMenuDto> roleMenuDtoList = new ArrayList<>();
        for (Long menuId : menuIds) {
            roleMenuDtoList.add(new RoleMenuDto(roleId,menuId));
        }
        roleMapper.authorizationBatch(roleMenuDtoList);
        return true;
    }

    /**
     * 查询角色详情
     * @return
     */
    @Override
    public Role getRoleInfo(Long roleId) {
        Role role = roleMapper.selectRoleInfoByRoleId(roleId);
        return role;
    }

    /**
     * 修改用户角色时查询的角色列表，信息不完整
     * @return
     */
    @Override
    public List<Role> getRoleList() {
        List<Role> roles = roleMapper.selectRoleList();
        return roles;
    }

    /**
     * 查询角色列表，完整信息
     * @return
     */
    @Override
    public List<Role> getRoleListDetails() {
        return roleMapper.selectRoleListDetails();
    }

    /**
     * 登录认证时获取角色数组
     * @param userId
     * @return
     */
    @Override
    public List<String> listStringRolesByUserId(Long userId) {
        return null;
    }
}
