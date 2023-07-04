package com.xinyiran.property.entity.ems;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 费用项设置表
 */
@Data
public class Setting {
    private Long id;
    private String name;
    private BigDecimal unitPrice;
    private String unit;
    private Integer type;
    private Integer cycle;
    private Integer delState;
    private Integer billingDate;
}
