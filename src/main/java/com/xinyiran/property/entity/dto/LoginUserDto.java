package com.xinyiran.property.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDto implements Serializable {
    private Long userId;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

}
