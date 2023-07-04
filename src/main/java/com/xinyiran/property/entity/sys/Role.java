package com.xinyiran.property.entity.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author xinyiran
 * @since 2022-05-08
 */
@Data
public class Role {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "角色名不能为空")
    private String name;

    @NotBlank(message = "角色标识不能为空")
    private String code;

    private List<Long> menuIds = new ArrayList<>();

    /**
     * 备注
     */
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private LocalDateTime created;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private LocalDateTime updated;

    private Integer state;


}
