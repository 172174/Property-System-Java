package com.xinyiran.property.security;


import com.alibaba.fastjson2.JSON;
import com.xinyiran.property.entity.sys.AccountLoginUser;
import com.xinyiran.property.utils.JwtUtil;
import com.xinyiran.property.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //从请求中获取token
        String token = request.getHeader(jwtUtil.getHeader());
        //判断token是否为空，空则放行
        if(!StringUtils.hasText(token) || "null".equals(token)){
            filterChain.doFilter(request,response);
            return;
        }
        //解析token
        Claims claimsByToken = jwtUtil.getClaimsByToken(token);
        if(Objects.isNull(claimsByToken)){
            throw new JwtException("登录已过期，请重新登录！");
        }
        if(jwtUtil.isTokenExpired(claimsByToken)){
            throw new JwtException("登录已过期，请重新登录！");
        }

        String subject = claimsByToken.getSubject();

        //获取redis中的id

        String userToJson = (String) redisUtil.get("login:" + subject);
        AccountLoginUser user = JSON.parseObject(userToJson, AccountLoginUser.class);
        if(Objects.isNull(user)){
            throw new JwtException("token失效请重新登录");
        }

        //TODO 获取权限

        //将token存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);
    }
}
