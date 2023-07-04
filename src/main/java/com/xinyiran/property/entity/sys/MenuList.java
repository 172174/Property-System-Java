package com.xinyiran.property.entity.sys;

import lombok.Data;

@Data
public class MenuList {
    private String title; //菜单标题
    private String name;
    private String icon;
    private String path; //路由跳转地址
    private String component;
}
