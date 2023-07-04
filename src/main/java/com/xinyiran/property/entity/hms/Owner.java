package com.xinyiran.property.entity.hms;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 业主表
 */
@Data
public class Owner{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String gender;
    private String idCard;
    private String phoneNum;
    private String houseNum;
    private Long houseId;
    private Long buildingId;
    private String avatar;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private LocalDateTime checkInTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private LocalDateTime movingOutTime;
    private Integer homeowner;
    private Integer state;
}
