package com.xinyiran.property.service.hms;

import com.xinyiran.property.entity.dto.LifeDosage;
import com.xinyiran.property.entity.hms.House;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface HouseService {
    List<House> getHouseList();

    List<House> getPage(House house);

    House getHouseDetails(Long houseId);

    LifeDosage getLifeDosage(Long houseId);

    Boolean save(House house);

    Boolean update(House house);

    Boolean delete(Long houseId);
}
