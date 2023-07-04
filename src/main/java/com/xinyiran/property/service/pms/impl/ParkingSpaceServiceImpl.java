package com.xinyiran.property.service.pms.impl;

import com.xinyiran.property.entity.pms.ParkingSpace;
import com.xinyiran.property.mapper.pms.ParkingSpaceMapper;
import com.xinyiran.property.service.pms.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpaceServiceImpl implements ParkingSpaceService {
    @Autowired
    private ParkingSpaceMapper parkingSpaceMapper;

    /**
     * 更新车位信息
     * @param parkingSpace
     * @return
     */
    @Override
    public Boolean update(ParkingSpace parkingSpace) {
        int is = parkingSpaceMapper.update(parkingSpace);
        if(is != 1){
            return false;
        }
        return true;
    }

    /**
     * 获得车位列表，分页
     * @return
     */
    @Override
    public List<ParkingSpace> getListPage(ParkingSpace parkingSpace) {
        return parkingSpaceMapper.selectList(parkingSpace);
    }

    /**
     * 车位详情
     * @param id
     * @return
     */
    @Override
    public ParkingSpace getDetail(Long id) {
        return parkingSpaceMapper.selectDetail(id);
    }
}
