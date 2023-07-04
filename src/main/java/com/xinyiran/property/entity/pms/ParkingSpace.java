package com.xinyiran.property.entity.pms;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 车位实体
 */
@Data
public class ParkingSpace {
    private Long id;
    private String number;
    private Integer type;
    private Float area;
    private Integer state;
    private Integer delState;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private LocalDateTime created;
    private String remark;
    private Integer isUse;
}
