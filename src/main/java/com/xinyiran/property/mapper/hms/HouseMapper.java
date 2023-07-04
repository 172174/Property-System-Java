package com.xinyiran.property.mapper.hms;

import com.xinyiran.property.entity.dto.HouseDto;
import com.xinyiran.property.entity.hms.Electricity;
import com.xinyiran.property.entity.hms.Gas;
import com.xinyiran.property.entity.hms.House;
import com.xinyiran.property.entity.hms.Water;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HouseMapper {
    List<House> selectHousesList();

    List<House> selectList(House house);

    List<HouseDto> selectListOfBuilding();

    House selectHouseDetails(Long houseId);


    Long binding(Long houseId, Long ownerId);

    int updateBinding(Long houseId,Long ownerId);

    Water findWaters(Long houseId);

    Gas findGas(Long houseId);

    Electricity findElectricity(Long houseId);

    Long save(House house);

    void update(House house);

    void updateCheckIntime(Long houseId);

    Long delete(Long houseId);
}
