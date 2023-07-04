package com.xinyiran.property.entity.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xinyiran
 * @since 2022-05-08
 */
@Data

public class User  {
    private Long id;

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "用户名不能为空")
    private String username;

    private String nickName;

    private String password;

    private String avatar;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private LocalDateTime created;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private LocalDateTime updated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private LocalDateTime lastLogin;

    @NotNull(message = "用户状态不能为空")
    private Integer state;

    @NotNull(message = "用户启用状态不能为空")
    private Integer enabledState;


    private Long ownerId;

    private String ownerName;

    private Role roles;


}
