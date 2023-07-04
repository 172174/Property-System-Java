package com.xinyiran.property.controller.hms;

import com.github.pagehelper.PageInfo;
import com.xinyiran.property.common.Result;
import com.xinyiran.property.controller.BaseController;
import com.xinyiran.property.entity.hms.Owner;
import com.xinyiran.property.service.hms.OwnerService;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/admin/hms")
public class OwnerController extends BaseController {

    @Autowired
    private OwnerService ownerService;

    /**
     * 业主列表
     * @return
     */
    @GetMapping("/owner/page")
    public Result page(Owner owner){
        startPage();
        List<Owner> ownerList = ownerService.getPage(owner);
        PageInfo<Owner> pageInfo = new PageInfo<>(ownerList);
        return Result.succ(pageInfo);
    }

    /**
     * 业主详情
     * @param ownerId
     * @return
     */
    @GetMapping("/owner/details/{ownerId}")
    public Result details(@PathVariable("ownerId")Long ownerId){
        return Result.succ(ownerService.getDetails(ownerId));
    }


    /**
     * 更新业主
     * @param owner
     * @return
     */
    @PostMapping("/owner/update")
    public Result update(@RequestBody()Owner owner){
        Boolean is = ownerService.editOwner(owner);
        if(!is){
            return Result.fail("修改失败，请稍后重试");
        }
        return Result.succ("修改成功",null);
    }

    /**
     * 新增业主
     * @param owner
     * @return
     */
    @PostMapping("/owner/save")
    public Result insert(@RequestBody()Owner owner){
        Boolean is = ownerService.addOwner(owner);
        if(!is){
            return Result.fail("添加失败，请稍后重试");
        }
        return Result.succ("添加成功",null);
    }

}
