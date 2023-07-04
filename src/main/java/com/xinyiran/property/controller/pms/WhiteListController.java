package com.xinyiran.property.controller.pms;

import com.github.pagehelper.PageInfo;
import com.xinyiran.property.common.Result;
import com.xinyiran.property.controller.BaseController;
import com.xinyiran.property.entity.pms.WhiteList;
import com.xinyiran.property.service.pms.WhiteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiteListController extends BaseController {
    @Autowired
    private WhiteListService whiteListService;

    /**
     * 车辆白名单分页
     * @param whiteList
     * @return
     */
    @GetMapping("/admin/pms/whitelist/page")
    public Result page(WhiteList whiteList){
        startPage();
        List<WhiteList> list = whiteListService.getPage(whiteList);
        PageInfo<WhiteList> pageInfo = new PageInfo<>(list);
        return Result.succ(pageInfo);
    }

    /**
     * 获得白名单车辆详情
     * @param whiteListId
     * @return
     */
    @GetMapping("/admin/pms/whitelist/detail/{id}")
    public Result detail(@PathVariable("id")Long whiteListId){
        return Result.succ(whiteListService.getDetail(whiteListId));
    }
}
