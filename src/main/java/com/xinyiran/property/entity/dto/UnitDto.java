package com.xinyiran.property.entity.dto;

import com.xinyiran.property.entity.hms.House;
import lombok.Data;

import java.util.List;

@Data
public class UnitDto {
    private Integer id;
    private String name;
    private Integer type = 1;
    private List<HouseDto> children;

}
