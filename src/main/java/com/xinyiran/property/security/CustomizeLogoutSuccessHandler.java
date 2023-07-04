package com.xinyiran.property.security;

import cn.hutool.json.JSONUtil;
import com.xinyiran.property.common.Result;
import com.xinyiran.property.utils.JwtUtil;
import com.xinyiran.property.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomizeLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        //设置响应头信息，获取响应体
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        //退出成功后，返回空的token信息给前端覆盖用户之前的token,清除redis里的用户
        response.setHeader(jwtUtil.getHeader(),"");
        redisUtil.del("login:"+authentication.getName());
        Result result = Result.succ("退出成功",null );
        //将返回信息写入响应体
        outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }
}
