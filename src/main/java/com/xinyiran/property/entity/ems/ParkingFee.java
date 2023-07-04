package com.xinyiran.property.entity.ems;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 停车费用实体
 */
@Data
public class ParkingFee {
    private Long id;
    private String numberPlate;
    private String name;//收费名称
    private Integer type;
    private Float quantity;
    private String unit;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private Integer state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private LocalDateTime creationTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private LocalDateTime endTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private LocalDateTime paymentTime;
    private String payMethod;
    private Integer delState;
}
