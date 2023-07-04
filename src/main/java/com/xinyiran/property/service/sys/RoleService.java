package com.xinyiran.property.service.sys;


import com.xinyiran.property.entity.sys.Role;
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
public interface RoleService {

    List<String> listStringRolesByUserId(Long userId);

    List<Role> listRolesByUserId(Long userId);

    List<Role> getRoleList();

    List<Role> getRoleListDetails();

    Role getRoleInfo(Long roleId);

    Boolean authorized(Long roleId,Long[] menuIds);
}
