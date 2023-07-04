package com.xinyiran.property.entity.hms;

import lombok.Data;

/**
 * 小区表
 */
@Data
public class Community {
    private Long id;
    private String name;
    private String number;
    private String location;
    private Float area;
    private Integer parkingSpaceCount;
    private Integer buildings;
    private Integer houses;
    private Integer state;
}
