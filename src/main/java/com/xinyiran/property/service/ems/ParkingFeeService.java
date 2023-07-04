package com.xinyiran.property.service.ems;

import com.xinyiran.property.entity.ems.ParkingFee;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface ParkingFeeService {
    Boolean generateTemporaryParkingFee(String numberPlate, LocalDateTime inTime);
    ParkingFee updateTemporaryParkingFee(String numberPlate);

    void inOrOut(String numberPlate);

    List<ParkingFee> getPage(ParkingFee parkingFee);
}
