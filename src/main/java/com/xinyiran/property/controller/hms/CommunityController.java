package com.xinyiran.property.controller.hms;

import com.xinyiran.property.common.Result;
import com.xinyiran.property.controller.BaseController;
import com.xinyiran.property.service.hms.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommunityController extends BaseController {

    @Autowired
    private CommunityService communityService;
    /**
     * 获取小区基本信息
     * @return
     */
    @GetMapping("")
    public Result info(){
        return Result.succ(communityService.getInfo());
    }
}
