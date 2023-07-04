package com.xinyiran.property.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class BuildingDto {
    private Long id;
    private String name;
    private Integer units;
    private Integer sort;
    private Integer type = 0;
    private List<UnitDto> children;
}
