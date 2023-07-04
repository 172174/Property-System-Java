package com.xinyiran.property.controller.ems;

import com.github.pagehelper.PageInfo;
import com.xinyiran.property.common.Result;
import com.xinyiran.property.controller.BaseController;
import com.xinyiran.property.entity.ems.Setting;
import com.xinyiran.property.service.ems.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SettingController extends BaseController {

    @Autowired
    private SettingService settingService;

    /**
     * 费用项列表分页
     * @return
     */
    @GetMapping("/admin/fees/settingPage")
    public Result listPage(){
        startPage();
        List<Setting> listPage = settingService.getListPage();
        PageInfo<Setting> pageInfo = new PageInfo<>(listPage);
        return Result.succ(pageInfo);
    }

    /**
     * 查询详情
     * @param feesId
     * @return
     */
    @GetMapping("/admin/fees/detail/{id}")
    public Result detail(@PathVariable("id")Long feesId){
        return Result.succ(settingService.getDetail(feesId));
    }
}
