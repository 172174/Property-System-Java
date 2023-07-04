package com.xinyiran.property.mapper.sys;

import com.xinyiran.property.entity.sys.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xinyiran
 * @since 2022-05-08
 */
@Mapper
public interface UserMapper {

    Long findOwnerInfo(Long userId);

    User selectUserInfoByUserName(String username);

    List<User> selectAdminList();

    List<User> selectUserList(User user);

    User selectUserInfoById(Long id);

    User getByUserName(String username);

    Long addUser(User user);
}
