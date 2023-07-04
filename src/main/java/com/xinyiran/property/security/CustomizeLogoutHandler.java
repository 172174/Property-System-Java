package com.xinyiran.property.security;

import com.xinyiran.property.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomizeLogoutHandler implements LogoutHandler {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        System.out.println(authentication);
        System.out.println(authentication.getName());
        redisUtil.del("login:"+authentication.getName());
    }
}
