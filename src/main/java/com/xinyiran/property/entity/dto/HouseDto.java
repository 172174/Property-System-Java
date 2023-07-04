package com.xinyiran.property.entity.dto;

import lombok.Data;

@Data
public class HouseDto {
    private Long id;
    private Long buildingId;
    private String name;
    private Integer type = 2;
    private Integer unit;
}
