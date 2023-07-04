package com.xinyiran.property.security;

import com.xinyiran.property.common.Const;
import com.xinyiran.property.common.exception.CaptchaException;
import com.xinyiran.property.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CaptchaFilter extends OncePerRequestFilter {
    @Autowired
    LoginFailureHandler loginFailureHandler;
    @Autowired
    RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        if(url.startsWith("/login1")  && request.getMethod().equals("POST")){
            try {
                //校验验证码
                validate(request);

            }catch (CaptchaException e){
                //认证失败处理器
                loginFailureHandler.onAuthenticationFailure(request,response,e);
            }

        }
        filterChain.doFilter(request,response);
    }

    private void validate(HttpServletRequest request) {
        //获取验证码和key
        System.out.println(request);
    String uuid = request.getParameter("uuid");
    String code = request.getParameter("code");

        if(StringUtils.isBlank(uuid) || StringUtils.isBlank(code)){
            throw new CaptchaException("验证码错误");
        }
        String redisCode = (String) redisUtil.get(Const.CAPTCHA_KEY+uuid);
        //通过key获得缓存在redis里验证码
        if(!code.equals(redisCode)){
            throw new CaptchaException("验证码错误");
        }
        //验证通过后清除缓存里的验证码
        redisUtil.del(Const.CAPTCHA_KEY+uuid);

    }
}
