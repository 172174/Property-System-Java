package com.xinyiran.property.controller.pms;

import com.github.pagehelper.PageInfo;
import com.xinyiran.property.common.Result;
import com.xinyiran.property.controller.BaseController;
import com.xinyiran.property.entity.pms.ParkingSpace;
import com.xinyiran.property.service.pms.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.PanelUI;
import java.util.List;

@RestController
public class ParkingController extends BaseController {

    @Autowired
    private ParkingSpaceService parkingSpaceService;

    /**
     * 车位列表
     * @return
     */
    @GetMapping("/admin/parkingSpace/listPage")
    public Result listPage(ParkingSpace parkingSpace){
        startPage();
        List<ParkingSpace> list = parkingSpaceService.getListPage(parkingSpace);
        PageInfo<ParkingSpace> pageInfo = new PageInfo<>(list);
        return Result.succ(pageInfo);
    }

    /**
     * 车位详情
     * @param id
     * @return
     */
    @GetMapping("/admin/pms/parkingSpace/detail/{id}")
    public Result detail(@PathVariable("id")Long id){
        return Result.succ(parkingSpaceService.getDetail(id));
    }


    @GetMapping("/admin/pms/parkingSpace/update")
    public Result update(@RequestBody()ParkingSpace parkingSpace){
        Boolean is = parkingSpaceService.update(parkingSpace);
        return Result.succ(null);
    }
}
