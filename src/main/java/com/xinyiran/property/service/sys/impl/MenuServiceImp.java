package com.xinyiran.property.service.sys.impl;


import cn.hutool.core.bean.BeanUtil;
import com.xinyiran.property.entity.sys.Menu;
import com.xinyiran.property.entity.sys.User;
import com.xinyiran.property.entity.dto.MenuDto;
import com.xinyiran.property.mapper.sys.MenuMapper;
import com.xinyiran.property.mapper.sys.UserMapper;
import com.xinyiran.property.service.sys.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xinyiran
 * @since 2022-05-08
 */
@Service
public class MenuServiceImp  implements MenuService {


    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserMapper userMapper;


    /**
     * 添加菜单
     * @param menu
     * @return
     */
    @Override
    public Boolean addMenu(Menu menu) {
        return menuMapper.insertMenu(menu);
    }

    /**
     * 获取menu实体树
     * @return
     */
    @Override
    public List<Menu> tree() {
        List<Menu> menuList = menuMapper.selectAllMenu();
        return buildTreeMenu(menuList);
    }


    /**
     *获取当前用户的导航栏
     * @param username
     * @return
     */
    @Override
    public List<MenuDto> getCurrentUserNav(String username) {
        User user = userMapper.getByUserName(username);
        List<MenuDto> menus = menuMapper.getMenuListByUserId(user.getId());
        List<MenuDto> menuList = buildTreeMenuDto(menus);
        return menuList;
    }


    /**
     * 将从数据中获得的menu实体转换为树状结构（将子菜单装入父菜单）
     * @param menus
     * @return
     */
    private List<MenuDto> buildTreeMenuDto(List<MenuDto> menus) {
        List<MenuDto> finalMenu = new ArrayList<>();
        for (MenuDto menu : menus){
            for (MenuDto e: menus){
                if(e.getParentId() == menu.getId()){
                    menu.getChildren().add(e);
                }
            }
            if (menu.getParentId() == 0L){
                finalMenu.add(menu);
            }
        }
        return finalMenu;
    }

    /**
     * 将从数据中获得的menu实体转换为树状结构（将子菜单装入父菜单）
     * @param menus
     * @return
     */
    private List<Menu> buildTreeMenu(List<Menu> menus) {
        List<Menu> finalMenu = new ArrayList<>();
        for (Menu menu : menus){
            for (Menu e: menus){
                if(e.getParentId().equals(menu.getId())){
                    menu.getChildren().add(e);
                }
            }
            if (menu.getParentId().equals(0L)){
                finalMenu.add(menu);
            }
        }
        return finalMenu;
    }


}
