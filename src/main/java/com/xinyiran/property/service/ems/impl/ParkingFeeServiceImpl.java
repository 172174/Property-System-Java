package com.xinyiran.property.service.ems.impl;

import com.xinyiran.property.entity.ems.ParkingFee;
import com.xinyiran.property.entity.ems.Setting;
import com.xinyiran.property.mapper.ems.ParkingFeeMapper;
import com.xinyiran.property.mapper.ems.SettingMapper;
import com.xinyiran.property.service.ems.ParkingFeeService;
import com.xinyiran.property.utils.SnowFlakeUtil;
import com.xinyiran.property.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingFeeServiceImpl implements ParkingFeeService{
    @Autowired
    private SettingMapper settingMapper;

    @Autowired
    private ParkingFeeMapper parkingFeeMapper;

    /**
     * 临时停车费，进场生成基本订单信息
     * @return
     */
    @Transactional
    @Override
    public Boolean generateTemporaryParkingFee(String numberPlate,LocalDateTime inTime) {
        Setting setting = settingMapper.selectById(5L);
        ParkingFee parkingFee = new ParkingFee();
        parkingFee.setId(SnowFlakeUtil.generateIdNum());
        parkingFee.setName(setting.getName());
        parkingFee.setUnit(setting.getUnit());
        parkingFee.setType(0);//0：临停收费
        parkingFee.setUnitPrice(setting.getUnitPrice());
        parkingFee.setState(0);//0：刚进入停车场未离开状态 1：离开停车场状态 2：支付完成 3：取消
        parkingFee.setDelState(0);
        parkingFee.setCreationTime(inTime);
        Long insertParkingFee = parkingFeeMapper.insertParkingFee(parkingFee);
        if(StringUtils.isNull(insertParkingFee)){
            return false;
        }
        return true;
    }
    /**
     * 临时停车费，离开生成费用信息
     * @return
     */
    @Transactional
    @Override
    public ParkingFee updateTemporaryParkingFee(String numberPlate) {
        ParkingFee parkingFee = parkingFeeMapper.selectByNumberPlate(numberPlate);
        if(StringUtils.isNull(parkingFee)){
            return null;
        }
        //计算停车时间
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(parkingFee.getCreationTime(),now);
        long minutes = duration.toMinutes();
        float quantity = minutes / 60;

        //计算总价格
        BigDecimal num = new BigDecimal(quantity).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal unitPrice = parkingFee.getUnitPrice();
        BigDecimal totalPrice = unitPrice.multiply(num);

        //赋值
        parkingFee.setQuantity(quantity);
        parkingFee.setEndTime(now);
        parkingFee.setState(1);//1：已经离开，准备支付
        parkingFee.setTotalPrice(totalPrice);

        //更新
        int isUpdate = parkingFeeMapper.updateParkingFee(parkingFee);
        if(StringUtils.isNull(isUpdate)){
            return null;
        }

        return parkingFee;
    }

    /**
     * 生成支付，未完成
     * @param numberPlate
     */
    @Override
    public void inOrOut(String numberPlate) {

    }

    /**
     * 订单列表
     * @param parkingFee
     * @return
     */
    @Override
    public List<ParkingFee> getPage(ParkingFee parkingFee) {
        return parkingFeeMapper.selectPage(parkingFee);
    }
}
