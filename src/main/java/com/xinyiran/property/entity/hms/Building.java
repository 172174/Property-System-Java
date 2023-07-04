package com.xinyiran.property.entity.hms;

import lombok.Data;

/**
 * 楼栋表
 */
@Data
public class Building {
    private Long id;
    private Long communityId;
    private String name;
    private Integer units;
    private Integer sort;
    private String number;
    private Integer state;
}
