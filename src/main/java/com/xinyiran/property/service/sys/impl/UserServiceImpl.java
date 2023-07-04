package com.xinyiran.property.service.sys.impl;

import com.xinyiran.property.entity.dto.UserOwnerDto;
import com.xinyiran.property.entity.hms.Owner;
import com.xinyiran.property.entity.sys.User;
import com.xinyiran.property.mapper.hms.OwnerMapper;
import com.xinyiran.property.mapper.sys.MenuMapper;
import com.xinyiran.property.mapper.sys.RoleMapper;
import com.xinyiran.property.mapper.sys.UserMapper;
import com.xinyiran.property.service.hms.OwnerService;
import com.xinyiran.property.service.sys.UserService;
import com.xinyiran.property.utils.JwtUtil;
import com.xinyiran.property.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private OwnerMapper ownerMapper;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;



    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @Override
    public User getUserInfoByUserId(Long userId) {
        User user = userMapper.selectUserInfoById(userId);
        return user;
    }

    /**
     * 获取管理员列表
     * @return
     */
    @Override
    public List<User> getAdminList() {
        return userMapper.selectAdminList();
    }

    /**
     * 获取用户列表
     * @return
     */
    @Override
    public List<User> getUserList(User user) {
        return userMapper.selectUserList(user);
    }

    /**
     * 通过用户名查询用户头像、id（登录成功后）
     * @param name
     * @return
     */
    @Override
    public User getUserInfoByUserName(String name) {
        User user = userMapper.selectUserInfoByUserName(name);
        return user;
    }

    /**
     * 用户端查询信息
     * @param name
     * @return
     */
    @Override
    public UserOwnerDto getUserInfoAndOwnerInfoByUserName(String name){
        UserOwnerDto userOwnerDto = new UserOwnerDto();
        User user = userMapper.selectUserInfoByUserName(name);
        Long ownerId = userMapper.findOwnerInfo(user.getId());
        if(ownerId != null){
            Owner owner = ownerMapper.selectOwnerDetails(ownerId);
            userOwnerDto.setOwnerId(ownerId);
            userOwnerDto.setOwnerName(owner.getName());
            userOwnerDto.setHomeowner(owner.getHomeowner());
            userOwnerDto.setHouseId(owner.getHouseId());
            userOwnerDto.setHouseNum(owner.getHouseNum());
            userOwnerDto.setIdCard(owner.getIdCard());
            userOwnerDto.setPhoneNum(owner.getPhoneNum());
        }
        userOwnerDto.setUserId(user.getId());
        userOwnerDto.setAvatar(user.getAvatar());
        userOwnerDto.setUsername(user.getUsername());

        return userOwnerDto;
    }


    /**
     * 用户端注册
     * @return
     */
    @Override
    public Boolean register(User user) {
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        user.setAvatar("https://xinyiran-mall.oss-cn-chengdu.aliyuncs.com/xinyiran/imgs/img/0Z234K23-4.jpg");
        user.setState(0);
        user.setNickName("普通用户");
        user.setEnabledState(0);
        Long aLong = userMapper.addUser(user);
        if(user.getId() != null){
            Long isrole = roleMapper.setRole(user.getId(), 3L);
            if(isrole != 1)
                return false;
            return true;
        }
        return false;
    }

    /**
     * 通过用户名查询用户（登录使用）
     * @param username
     * @return
     */
    @Override
    public User getByUserName(String username) {
        User user = userMapper.getByUserName(username);
        return user;
    }




    /**
     * 通过id获取权限信息
     * @param user
     * @return
     */
    @Override
    public String getAuthorityByUser(User user) {
        String authority= "";
        //判断redis中是否存有权限信息，有则直接从redis中取出
        if(redisUtil.hasKey("GrantedAuthority:"+user.getUsername())){
            authority = (String) redisUtil.get("GrantedAuthority:"+user.getUsername());
            return authority;
        }

        //获取角色编码
        List<String> roles = roleMapper.getRolesByUserId(user.getId());
        if(roles.size()>0){
            String roleCodes = roles.stream().map(r ->"ROLE_"+ r).collect(Collectors.joining(","));
            authority = roleCodes.concat(",");
        }

        //获取菜单编码
        List<String> menus = menuMapper.getMenusByUserId(user.getId());
        if(menus.size()>0){
            String menuPerms = menus.stream().map(r ->"ROLE_"+ r).collect(Collectors.joining(","));
            authority = authority.concat(menuPerms);
        }

        redisUtil.set("GrantedAuthority:"+user.getUsername(),authority,60*60);
        return authority;
    }
}
