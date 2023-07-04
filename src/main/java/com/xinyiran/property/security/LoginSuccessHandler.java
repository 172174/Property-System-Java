package com.xinyiran.property.security;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.xinyiran.property.common.Const;
import com.xinyiran.property.common.Result;
import com.xinyiran.property.entity.dto.UserOwnerDto;
import com.xinyiran.property.entity.sys.AccountLoginUser;
import com.xinyiran.property.entity.sys.User;
import com.xinyiran.property.service.sys.UserService;
import com.xinyiran.property.utils.JwtUtil;
import com.xinyiran.property.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;



/**
 * 登录成功处理机
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //设置响应头信息，获取响应体
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();

        //获取信息
        int loginType = Integer.parseInt(httpServletRequest.getParameter("loginType"));


        // 生成JWT，并放置到请求头中
        String token = jwtUtil.createToken(authentication.getName());
        response.setHeader(jwtUtil.getHeader(), token);
        AccountLoginUser user = (AccountLoginUser) authentication.getPrincipal();
        String userToJson = JSON.toJSONString(user);
        redisUtil.set("login:"+authentication.getName(),userToJson, Const.REDIS_EXPIRE);
        UserOwnerDto userInfo = null;
        if(loginType == 1){
            userInfo = userService.getUserInfoAndOwnerInfoByUserName(user.getUsername());

        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("token",token);
        map.put("userInfo",userInfo);

        Result result = Result.succ("登录成功",map);
        System.out.println(result);
        //将返回信息写入响应体
        outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }
}

