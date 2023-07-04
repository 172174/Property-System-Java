package com.xinyiran.property.service.hms.impl;

import com.xinyiran.property.entity.dto.LifeDosage;
import com.xinyiran.property.entity.hms.Electricity;
import com.xinyiran.property.entity.hms.Gas;
import com.xinyiran.property.entity.hms.House;
import com.xinyiran.property.entity.hms.Water;
import com.xinyiran.property.mapper.hms.BuildingMapper;
import com.xinyiran.property.mapper.hms.HouseMapper;
import com.xinyiran.property.service.hms.HouseService;
import com.xinyiran.property.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private BuildingMapper buildingMapper;

    /**
     * 获得住房详情
     * @param houseId
     * @return
     */
    @Override
    public House getHouseDetails(Long houseId) {
        House house = houseMapper.selectHouseDetails(houseId);
        return house;
    }

    /**
     * 获取水电气当前使用量
     * @param houseId
     * @return
     */
    @Override
    public LifeDosage getLifeDosage(Long houseId) {
        if(StringUtils.isNull(houseId)){
            return null;
        }
        LifeDosage lifeDosage = new LifeDosage();
        Water water = houseMapper.findWaters(houseId);
        if(water != null){
            lifeDosage.setWater((float)(Math.round((water.getTotalWater()- water.getLastTotalWater())*100))/100);
        }
        Gas gas = houseMapper.findGas(houseId);
        if(gas != null){
            lifeDosage.setGas((float)(Math.round((gas.getTotalGas()-gas.getLastTotalGas())*100))/100);
        }
        Electricity electricity = houseMapper.findElectricity(houseId);
        if(electricity != null){
            lifeDosage.setElectricity((float)(Math.round((electricity.getTotalElectricity()-electricity.getLastTotalElectricity())*100))/100);
        }
        return lifeDosage;
    }

    /**
     * 添加新住宅
     * @param house
     * @return
     */
    @Override
    public Boolean save(House house) {
        if(house.getBuildingId() == null)
            return false;
        String buildName = buildingMapper.findNameById(house.getBuildingId());
        house.setBuildingName(buildName);
        Long i = houseMapper.save(house);
        return i == 1;
    }


    /**
     * 修改
     * @param house
     * @return
     */
    @Override
    public Boolean update(House house) {
        if(house.getBuildingId() == null)
            return false;
        String buildName = buildingMapper.findNameById(house.getBuildingId());
        house.setBuildingName(buildName);
        houseMapper.update(house);
        return true;
    }

    /**
     * 删除房屋
     * @param houseId
     * @return
     */
    @Override
    public Boolean delete(Long houseId) {
        Long i = houseMapper.delete(houseId);
        if(i == 1){
            return true;
        }
        return false;
    }

    /**
     * 获取房屋列表
     * @return
     */
    @Override
    public List<House> getHouseList() {
        return houseMapper.selectHousesList();
    }

    /**
     * 获取房屋列表,分页
     * @return
     */
    @Override
    public List<House> getPage(House house) {
        return houseMapper.selectList(house);
    }
}
