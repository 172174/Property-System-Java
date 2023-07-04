package com.xinyiran.property.controller.pms;

import com.github.pagehelper.PageInfo;
import com.xinyiran.property.common.Result;
import com.xinyiran.property.controller.BaseController;
import com.xinyiran.property.entity.pms.Record;
import com.xinyiran.property.service.pms.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecordController extends BaseController {
    @Autowired
    private RecordService recordService;

    /**
     * 获取车辆进场记录分页
     * @param record
     * @return
     */
    @GetMapping("/admin/pms/record/page")
    public Result page(Record record){
        System.out.println(record);
        startPage();
        List<Record> list = recordService.getPage(record);
        PageInfo<Record> pageInfo = new PageInfo<>(list);
        return Result.succ(pageInfo);
    }
}
