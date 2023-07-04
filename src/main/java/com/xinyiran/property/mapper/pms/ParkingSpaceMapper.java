package com.xinyiran.property.mapper.pms;

import com.xinyiran.property.entity.pms.ParkingSpace;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParkingSpaceMapper {
    List<ParkingSpace> selectList(ParkingSpace parkingSpace);

    ParkingSpace selectDetail(Long id);

    int update(ParkingSpace parkingSpace);

    Long insert(ParkingSpace parkingSpace);
}
