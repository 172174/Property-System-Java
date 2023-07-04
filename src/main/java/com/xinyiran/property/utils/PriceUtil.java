package com.xinyiran.property.utils;

import java.math.BigDecimal;

public class PriceUtil {
    //单商品总价计算
    public static BigDecimal SingleGoodsTotal(BigDecimal price,BigDecimal promotionPrice,Integer quantity,Integer type){
        BigDecimal totalPrice;
        totalPrice = price.multiply(new BigDecimal(quantity));
        return totalPrice;
    }

    //单商品实际价格计算（有优惠按优惠价，无则按原价）
    public static BigDecimal SingleGoodsActual(BigDecimal price,BigDecimal promotionPrice,Integer quantity,Integer type){
        BigDecimal ActualPrice;
        if(type == 1){
            ActualPrice = promotionPrice.multiply(new BigDecimal(quantity));
        }else{
            ActualPrice = price.multiply(new BigDecimal(quantity));
        }
        return ActualPrice;
    }
}
