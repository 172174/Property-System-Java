package com.xinyiran.property.service.hms;

import com.xinyiran.property.entity.dto.BuildingDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BuildingService {
    List<BuildingDto> buildingTree();

}
