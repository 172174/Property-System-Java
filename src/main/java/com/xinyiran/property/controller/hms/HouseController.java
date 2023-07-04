package com.xinyiran.property.controller.hms;

import com.github.pagehelper.PageInfo;
import com.xinyiran.property.common.Result;
import com.xinyiran.property.controller.BaseController;
import com.xinyiran.property.entity.hms.House;
import com.xinyiran.property.service.hms.BuildingService;
import com.xinyiran.property.service.hms.HouseService;
import com.xinyiran.property.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hms")
public class HouseController extends BaseController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private BuildingService buildingService;

    /**
     * 获取住房列表,分页
     * @return
     */
    @GetMapping("/house/page")
    public Result page(House house){
        startPage();
        List<House> houseList = houseService.getPage(house);
        PageInfo<House> pageInfo = new PageInfo<>(houseList);
        return Result.succ(pageInfo);
    }

    /**
     * 获取住房详情
     * @param houseId
     * @return
     */
    @GetMapping("/house/info/{houseId}")
    public Result info(@PathVariable("houseId")Long houseId){
        return Result.succ(houseService.getHouseDetails(houseId));
    }

    /**
     * 添加房屋
     * @param house
     * @return
     */
    @PostMapping("/house/save")
    public Result save(@RequestBody()House house){
        Boolean is = houseService.save(house);
        if(is){
            return  Result.succ("添加成功",null);
        }
        return  Result.fail("添加失败");
    }

    /**
     * 修改房屋
     * @param house
     * @return
     */
    @PostMapping("/house/update")
    public Result update(@RequestBody()House house){
        if(StringUtils.isNull(house))
            return Result.fail("更新失败");
        Boolean update = houseService.update(house);
        if(update)
            return Result.succ("更新成功",null);
        return Result.fail("更新失败");
    }

    /**
     * 删除房屋
     * @param houseId
     * @return
     */
    @GetMapping("/house/delete/{id}")
    public Result delete(@PathVariable("id")Long houseId){
        if(houseId == null)
            return Result.fail("删除失败");
        Boolean is = houseService.delete(houseId);
        if(is)
            return Result.succ("删除成功",null);
        return Result.fail("删除失败");
    }




}
