package com.xinyiran.property.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
public class MenuDto implements Serializable {
    private Long id;
    private String title;//对应数据库 name
    private Long parentId;
    private String name;//对应数据库 perm
    private String icon;
    private String path;
    private String component;
    private Integer orderNum;
    private List<MenuDto> children = new ArrayList<>();
}
