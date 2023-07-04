package com.xinyiran.property.entity.hms;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 住宅表
 */
@Data
public class House {
    private Long id;
    private Long buildingId;
    private String houseNum;
    private String buildingName;
    private Integer unit;
    private String floor;
    private String type;//房屋类型：住宅、商铺、工作室
    private String unitType;//户型
    private Float area;
    private Integer sale;
    private Integer state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private LocalDateTime checkInTime;
    private String remark;
    private List<Owner> owners;
}
