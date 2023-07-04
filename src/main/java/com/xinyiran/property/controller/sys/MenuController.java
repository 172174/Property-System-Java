package com.xinyiran.property.controller.sys;

import cn.hutool.core.map.MapUtil;
import com.xinyiran.property.common.Result;
import com.xinyiran.property.entity.dto.MenuDto;
import com.xinyiran.property.entity.sys.Menu;
import com.xinyiran.property.service.sys.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;
    //导航栏
    @GetMapping("/admin/nav")
    public Result nav(Principal principal){

        //获取权限信息
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        //获取导航栏信息
        List<MenuDto> nav = menuService.getCurrentUserNav(principal.getName());
        return Result.succ(MapUtil.builder()
                .put("authority",authorities)
                .put("nav",nav)
                .map()
        );
    }

    /**
     * 菜单列表
     * @return
     */
    @GetMapping("/admin/menu/menuList")
    public Result list(){
        return Result.succ(menuService.tree());
    }

    /**
     * 新增菜单
     * @return
     */
    @PostMapping("/admin/menu/save")
    public Result add(@RequestBody Menu menu){
        System.out.println(menu);
        Boolean aBoolean = menuService.addMenu(menu);
        if(aBoolean.equals(true)){
            return Result.succ("添加成功",null);
        }
        return Result.fail("添加失败");
    }
}
