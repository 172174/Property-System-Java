package com.xinyiran.property;

import com.xinyiran.property.entity.dto.MenuDto;
import com.xinyiran.property.mapper.sys.MenuMapper;
import com.xinyiran.property.mapper.sys.RoleMapper;
import com.xinyiran.property.service.sys.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class XinyiranPropertyApplicationTests {

	@Autowired
	private MenuService menuService;

	@Autowired
	private MenuMapper menuMapper;



	@Autowired
	private RoleMapper roleMapper;

	@Test
	void contextLoads() {
		String authority= "";
		//获取角色编码
		List<String> roles = roleMapper.getRolesByUserId(1L);
		if(roles.size()>0){
			String roleCodes = roles.stream().map(r ->"ROLE_"+ r).collect(Collectors.joining(","));
			authority = roleCodes.concat(",");
		}
		System.out.println(authority);
		List<String> menus = menuMapper.getMenusByUserId(1L);
		if(menus.size()>0){
            String menuPerms = menus.stream().map(r -> r).collect(Collectors.joining(","));
            authority = authority.concat(menuPerms);
        }
		System.out.println(authority);
	}

	@Test
	void PASSWORD(){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String s = encoder.encode("123");
		System.out.println(s);
	}

	@Test
	void MenuList(){
		List<MenuDto> admin = menuService.getCurrentUserNav("admin");
		System.out.println(admin);
	}

	@Test
	void Time(){
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime time = LocalDateTime.of(2023, 2, 27, 10, 30, 26);
		Duration duration = Duration.between(time,now);
		long hours = duration.toHours();
		long minutes = duration.toMinutes();
		BigDecimal decimal = new BigDecimal(10.0);
		float a = (float) minutes / 60;
		System.out.println("分钟："+a);
		BigDecimal   b  =   new  BigDecimal(a);
		BigDecimal decimal1 = b.setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal multiply = decimal.multiply(decimal1);
		long quantity = (minutes / 60 + (minutes % 60 != 0 ? 1 : 0));
		System.out.println("分钟："+minutes);
		System.out.println("小时"+quantity);
		System.out.println("小时:"+multiply);
	}


}
