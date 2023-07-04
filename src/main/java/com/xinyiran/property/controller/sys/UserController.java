package com.xinyiran.property.controller.sys;

import com.github.pagehelper.PageInfo;
import com.xinyiran.property.common.Result;
import com.xinyiran.property.controller.BaseController;
import com.xinyiran.property.entity.sys.User;
import com.xinyiran.property.service.sys.UserService;
import com.xinyiran.property.service.sys.impl.CaptchaService;
import com.xinyiran.property.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;


@RestController
public class UserController extends BaseController {

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private UserService userService;

    /**
     * 获取验证码
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/captchaImage")
    public Result captchaImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return captchaService.getPictureCaptcha(response);
    }

    /**
     * 获取用户信息
     * @param principal
     * @return
     */
    @GetMapping("/admin/userInfo")
    public Result userInfo(Principal principal){
        return Result.succ(userService.getUserInfoByUserName(principal.getName()));
    }

    /**
     * 获取用户列表，分页
     * @return
     */
    @GetMapping("/admin/list")
    public Result AdminPage(){
        startPage();
        List<User> users = userService.getAdminList();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return Result.succ(pageInfo);
    }

    @GetMapping("/admin/user/page")
    public Result UserPage(User user){
        startPage();
        List<User> users = userService.getUserList(user);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return Result.succ(pageInfo);
    }


    /**
     * 查询用户信息
     * @return
     */
    @GetMapping("/admin/info/{userId}")
    public Result list(@PathVariable("userId")Long userId){;
        return Result.succ(userService.getUserInfoByUserId(userId));
    }

    /**
     * 用户端注册
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody()User user){
        System.out.println(user);
        if(StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())){
            return Result.succ(201,"用户名或密码不能为空",null);
        }
        User user1 = userService.getByUserName(user.getUsername());
        if(user1 != null){
            return Result.succ(201,"该用户名已存在",null);
        }
        Boolean is = userService.register(user);
        if(is){
            return Result.succ("注册成功",null);
        }
        return  Result.succ(201,"注册失败，请稍后重试",null);
    }

}
