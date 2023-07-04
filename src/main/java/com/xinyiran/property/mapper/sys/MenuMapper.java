package com.xinyiran.property.mapper.sys;

import com.xinyiran.property.entity.dto.MenuDto;
import com.xinyiran.property.entity.sys.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<String> getMenusByUserId(Long userId);

    List<MenuDto> getMenuListByUserId(Long userId);

    List<Menu> selectAllMenu();

    Boolean insertMenu(Menu menu);
}
