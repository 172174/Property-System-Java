package com.xinyiran.property.service.hms.impl;

import com.xinyiran.property.entity.dto.BuildingDto;
import com.xinyiran.property.entity.dto.HouseDto;
import com.xinyiran.property.entity.dto.UnitDto;
import com.xinyiran.property.entity.hms.House;
import com.xinyiran.property.mapper.hms.BuildingMapper;
import com.xinyiran.property.mapper.hms.HouseMapper;
import com.xinyiran.property.service.hms.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;

    @Autowired
    private HouseMapper houseMapper;

    @Override
    public List<BuildingDto>  buildingTree(){
        List<HouseDto> houses = houseMapper.selectListOfBuilding();
        List<BuildingDto> buildings = buildingMapper.selectList();

        for (BuildingDto building : buildings) {
            Integer units = building.getUnits();
            List<UnitDto> unitDtos = new ArrayList<>();
            for (int i = 1; i <= units; i++) {
                UnitDto dto = new UnitDto();
                List<HouseDto> list = new ArrayList<>();
                for (HouseDto house : houses) {
                    if(house.getBuildingId() == building.getId()){
                        if(house.getUnit() == i){
                           list.add(house);
                        }
                    }
                }
                dto.setId(i);
                dto.setName(String.valueOf(i));
                dto.setChildren(list);
                unitDtos.add(dto);
            }
            building.setChildren(unitDtos);
        }

        return buildings;

    }
}
