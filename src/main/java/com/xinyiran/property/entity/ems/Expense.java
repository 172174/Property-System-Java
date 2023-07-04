package com.xinyiran.property.entity.ems;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 费用实体
 */
@Data
public class Expense {
    private Long id;
    private Long houseId;
    private String houseNum;
    private String ownerName;
    private Long ownerId;
    private String typeName;//费用类型名称
    private Float quantity;
    private String unit;//单位
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private Integer state;//0代缴费 1已缴费 2欠费
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private LocalDateTime creationTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/shanghai")
    private LocalDateTime deadline;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/shanghai")
    private LocalDateTime paymentTime;
    private Integer delState;

}
