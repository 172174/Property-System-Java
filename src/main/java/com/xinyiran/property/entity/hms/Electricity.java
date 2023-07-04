package com.xinyiran.property.entity.hms;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/*
电表实体
 */
@Data
public class Electricity {
    private Long id;
    private Long houseId;
    private Float totalElectricity;
    private Float lastMonthElectricity;
    private Float lastTotalElectricity;
    private Integer delState;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private LocalDateTime meterReadingTime;
}
