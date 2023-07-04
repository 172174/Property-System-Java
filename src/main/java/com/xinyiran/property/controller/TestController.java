package com.xinyiran.property.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyiran.property.common.Result;
import com.xinyiran.property.entity.sys.User;
import com.xinyiran.property.mapper.sys.UserMapper;
import com.xinyiran.property.service.hms.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

        @Autowired
        UserMapper userMapper;

        @Autowired
        private BuildingService buildingService;

        @PreAuthorize("hasRole('sys:role:list')")
        @RequestMapping("/admin/test")
        public Result test(Principal principal){

            return Result.succ("test-------",principal);
        }



    @GetMapping("/test3")
    public Result test3(){
        return Result.succ("test3");
    }
    }

