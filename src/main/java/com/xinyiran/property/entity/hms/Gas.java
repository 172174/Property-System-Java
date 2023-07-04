package com.xinyiran.property.entity.hms;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Gas {
    private Long id;
    private Long houseId;
    private Float totalGas;
    private Float lastMonthGas;
    private Float lastTotalGas;
    private Integer delState;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private LocalDateTime meterReadingTime;
}
