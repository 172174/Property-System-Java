package com.xinyiran.property.service.pms;

import com.xinyiran.property.entity.pms.ParkingSpace;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParkingSpaceService {
    List<ParkingSpace> getListPage(ParkingSpace parkingSpace);

    ParkingSpace getDetail(Long id);

    Boolean update(ParkingSpace parkingSpace);
}
