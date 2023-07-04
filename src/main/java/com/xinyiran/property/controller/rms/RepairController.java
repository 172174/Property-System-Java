package com.xinyiran.property.controller.rms;

import com.github.pagehelper.PageInfo;
import com.xinyiran.property.common.Result;
import com.xinyiran.property.controller.BaseController;
import com.xinyiran.property.entity.hms.Owner;
import com.xinyiran.property.entity.rms.Repair;
import com.xinyiran.property.service.rms.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/rms")
public class RepairController extends BaseController {
    @Autowired
    private RepairService repairService;

    /**
     * 列表，分页
     * @return
     */
    @GetMapping("/repair/page")
    public Result page(Repair repair){
        startPage();
        List<Repair> repairs = repairService.getPage(repair);
        PageInfo<Repair> pageInfo = new PageInfo<>(repairs);
        return Result.succ(pageInfo);
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @GetMapping("/repair/detail/{id}")
    public Result detail(@PathVariable("id")Long id){
        return Result.succ(repairService.getDetail(id));
    }

    @PostMapping("/repair/update")
    public Result update(@RequestBody() Repair repair){
        Boolean is = repairService.update(repair);
        if(!is){
            return Result.fail("指派失败，请稍后重试");
        }
        return Result.succ("指派成功",null);
    }

    @GetMapping("/repair/finish/{id}")
    public Result finish(@PathVariable("id") Long id){
        Boolean is = repairService.finish(id);
        if(!is){
            return Result.fail("失败，请稍后重试");
        }
        return Result.succ("成功",null);
    }
}
