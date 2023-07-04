package com.xinyiran.property.entity.pms;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 车辆白名单实体
 */
@Data
public class WhiteList {
    private Long id;
    private Long parkingSpaceId;
    private String parkingSpaceNumber;
    private Long ownerId;
    private String ownerName;
    private String numberPlate;
    private Integer type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private LocalDateTime startingTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private LocalDateTime endTime;
    private Integer state;
    private Integer delState;


}
