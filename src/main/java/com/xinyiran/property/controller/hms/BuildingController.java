package com.xinyiran.property.controller.hms;

import com.xinyiran.property.common.Result;
import com.xinyiran.property.service.hms.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    /**
     * 住宅树
     * @return
     */
    @GetMapping("/admin/building/houseTree")
    public Result houseTree(){
        return Result.succ(buildingService.buildingTree());
    }
}
