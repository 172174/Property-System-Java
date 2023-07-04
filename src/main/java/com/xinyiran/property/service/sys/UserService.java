package com.xinyiran.property.service.sys;

import com.xinyiran.property.entity.dto.UserOwnerDto;
import com.xinyiran.property.entity.sys.User;
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
public interface UserService {

    Boolean register(User user);

    //用户名查询用户（登录时使用）
    User getByUserName(String username);

    //获取权限（登录认证时使用）
    String getAuthorityByUser(User user);

    //登录成功后获取头像、id、用户名信息
    User getUserInfoByUserName(String name);


    List<User> getUserList(User user);

    //查询管理员列表,后台
    List<User> getAdminList();

    //查询用户列表,后台
//    List<User> getUserList();

    //查询用户信息
    User getUserInfoByUserId(Long id);



    UserOwnerDto getUserInfoAndOwnerInfoByUserName(String name);
}
