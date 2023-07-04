package com.xinyiran.property.controller.sys;

import com.github.pagehelper.PageInfo;
import com.xinyiran.property.common.Result;
import com.xinyiran.property.controller.BaseController;
import com.xinyiran.property.entity.dto.RoleMenuDto;
import com.xinyiran.property.entity.sys.Role;
import com.xinyiran.property.service.sys.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    /**
     * 用户修改角色时查询角色列表（只包含id和名称）
     * @return
     */
    @GetMapping("/admin/roleList")
    public Result list() {
        return Result.succ(roleService.getRoleList());
    }

    /**
     * 查询角色列表,分页
     * @return
     */
    @GetMapping("/admin/roleListPage")
    public Result listPage() {
        startPage();
        List<Role> roleList = roleService.getRoleListDetails();
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        return Result.succ(pageInfo);
    }

    /**
     * 查询角色详情附带菜单信息
     * @param roleId
     * @return
     */
    @GetMapping("/admin/role/info/{roleId}")
    public Result info(@PathVariable("roleId") Long roleId){
        return Result.succ(roleService.getRoleInfo(roleId));
    }

    /**
     * 分配权限
     * @param roleId
     * @param menuIds
     * @return
     */
    @PostMapping("/admin/role/perm/{roleId}")
    public Result perm(@PathVariable("roleId")Long roleId, @RequestBody Long[] menuIds){
        return Result.succ("分配权限成功",roleService.authorized(roleId,menuIds));
    }
}

