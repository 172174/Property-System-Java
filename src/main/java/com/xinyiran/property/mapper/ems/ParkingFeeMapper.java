package com.xinyiran.property.mapper.ems;

import com.xinyiran.property.entity.ems.ParkingFee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParkingFeeMapper {
    /**
     * 插入临停首次订单信息
     * @param parkingFee
     * @return
     */
    Long insertParkingFee(ParkingFee parkingFee);

    /**
     * 通过车牌号查找订单信息
     * @param numberPlate
     * @return
     */
    ParkingFee selectByNumberPlate(String numberPlate);

    /**
     * 更新临停订单费用相关信息
     * @param parkingFee
     * @return
     */
    int updateParkingFee(ParkingFee parkingFee);

    /**
     * 列表分页
     * @param parkingFee
     * @return
     */
    List<ParkingFee> selectPage(ParkingFee parkingFee);
}
