package com.xinyiran.property.service.sys;


import com.xinyiran.property.entity.sys.Menu;
import com.xinyiran.property.entity.dto.MenuDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xinyiran
 * @since 2022-05-08
 */
@Service
public interface MenuService {


    Boolean addMenu(Menu menu);

    List<Menu> tree();


    List<MenuDto> getCurrentUserNav(String username);
}
