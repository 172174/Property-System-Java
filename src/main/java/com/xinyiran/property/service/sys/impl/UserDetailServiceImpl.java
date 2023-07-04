package com.xinyiran.property.service.sys.impl;


import com.xinyiran.property.entity.sys.AccountLoginUser;
import com.xinyiran.property.entity.sys.User;
import com.xinyiran.property.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUserName(username);
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("用户账号或密码错误");
        }
        return new AccountLoginUser(user.getId(),user.getUsername(),user.getPassword(),getUserAuthority(user));
    }

    //获取用户权限
    public List<GrantedAuthority> getUserAuthority(User user){
        //角色（ROLE_admin）、菜单权限sys:user:list
        String authority = userService.getAuthorityByUser(user);

        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
    }
}
