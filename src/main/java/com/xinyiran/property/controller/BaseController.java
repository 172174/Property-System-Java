package com.xinyiran.property.controller;

import com.github.pagehelper.PageHelper;
import com.xinyiran.property.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    @Autowired
    HttpServletRequest request;

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        int pageNum = ServletRequestUtils.getIntParameter(request,"pageNum",1);
        int pageSize = ServletRequestUtils.getIntParameter(request,"pageSize",10);
        PageHelper.startPage(pageNum,pageSize);
    }
}
