package com.xinyiran.property.controller.ems;

import com.github.pagehelper.PageInfo;
import com.xinyiran.property.common.Result;
import com.xinyiran.property.controller.BaseController;
import com.xinyiran.property.entity.ems.ParkingFee;
import com.xinyiran.property.service.ems.ParkingFeeService;
import com.xinyiran.property.service.pms.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ParkingFeeController extends BaseController {
    @Autowired
    private ParkingFeeService parkingFeeService;

    @Autowired
    private RecordService recordService;

    /**
     * 车辆进入生成订单与记录
     * @param numberPlate
     * @return
     */
    @GetMapping("/admin/pms/parkingFee/in/{numberPlate}")
    public Result in(@PathVariable("numberPlate")String numberPlate){
        LocalDateTime inTime = LocalDateTime.now();
        Boolean is = parkingFeeService.generateTemporaryParkingFee(numberPlate,inTime);
        if(is == false){
            return Result.fail("车辆进入信息生成错误！");
        }
        recordService.inRecord(numberPlate,0,inTime);
        return Result.succ(true);
    }

    /**
     * 分页列表
     * @param parkingFee
     * @return
     */
    @GetMapping("/admin/pms/parkingFee/page")
    public Result page(ParkingFee parkingFee){
        startPage();
        List<ParkingFee> list = parkingFeeService.getPage(parkingFee);
        PageInfo<ParkingFee> pageInfo = new PageInfo<>(list);
        return Result.succ(pageInfo);
    }
}
