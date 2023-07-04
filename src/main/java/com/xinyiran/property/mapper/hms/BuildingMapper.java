package com.xinyiran.property.mapper.hms;

import com.xinyiran.property.entity.dto.BuildingDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BuildingMapper {

    List<BuildingDto> selectList();

    String findNameById(Long buildingId);
}
