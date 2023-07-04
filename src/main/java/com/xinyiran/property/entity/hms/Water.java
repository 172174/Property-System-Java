package com.xinyiran.property.entity.hms;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Water {
    private Long id;
    private Long houseId;
    private Float totalWater;
    private Float lastMonthWater;
    private Float lastTotalWater;
    private Integer delState;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private LocalDateTime meterReadingTime;
}
