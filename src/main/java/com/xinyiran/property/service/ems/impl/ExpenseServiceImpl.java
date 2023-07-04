package com.xinyiran.property.service.ems.impl;

import com.xinyiran.property.entity.ems.Expense;
import com.xinyiran.property.entity.ems.Setting;
import com.xinyiran.property.entity.hms.House;
import com.xinyiran.property.entity.hms.Owner;
import com.xinyiran.property.mapper.ems.ExpenseMapper;
import com.xinyiran.property.mapper.ems.SettingMapper;
import com.xinyiran.property.mapper.hms.HouseMapper;
import com.xinyiran.property.service.ems.ExpenseService;
import com.xinyiran.property.utils.SnowFlakeUtil;
import com.xinyiran.property.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private SettingMapper settingMapper;

    @Autowired
    private ExpenseMapper  expenseMapper;

    /**
     * 查找费用列表，分页
     * @param expense
     * @return
     */
    @Override
    public List<Expense> getListPage(Expense expense) {
        return expenseMapper.selectListPage(expense);
    }

    /**
     * 订单详情
     * @param expensId
     * @return
     */
    @Override
    public Expense getDetail(Long expensId) {
        return expenseMapper.selectDetail(expensId);
    }

    /**
     * 通过房间id查询物业费用
     * @param houseId
     * @return
     */
    @Override
    public List<Expense> getListByHouseId(Long houseId) {
        return expenseMapper.findByHouseId(houseId);
    }


    @Override
    public Boolean pay(Long expensId) {
        expenseMapper.pay(expensId);
        return true;
    }


    /**
     * 生成所有费用订单
     * @param houseId
     */
    @Override
    public Boolean  generateOrder(Long houseId){
        Boolean propertyOrder = generatePropertyOrder(houseId);
        Boolean waterOrder = generateWaterOrder(houseId);
        Boolean gasOrder = generateGasOrder(houseId);
        Boolean electricOrder = generateElectricOrder(houseId);
        return true;
    }

    /**
     * 生成水费用订单
     * @param houseId
     */
    @Transactional
    @Override
    public Boolean generateWaterOrder(Long houseId) {
        Float quantity = expenseMapper.selectWater(houseId);
        if(quantity == null){
            return false;
        }
        Setting setting = settingMapper.selectById(1L);
        if(StringUtils.isNull(setting)){
            return false;
        }
        Expense expense = generateExpense(quantity, houseId, setting);
        Long id = expenseMapper.insertExpense(expense);
        return true;
    }

    /**
     * 生成气费用订单
     * @param houseId
     */
    @Transactional
    @Override
    public Boolean generateGasOrder(Long houseId) {
        Float quantity = expenseMapper.selectGas(houseId);
        if(quantity == null){
            return false;
        }
        Setting setting = settingMapper.selectById(4L);
        if(StringUtils.isNull(setting)){
            return false;
        }
        Expense expense = generateExpense(quantity, houseId, setting);
        Long id = expenseMapper.insertExpense(expense);
        return true;
    }

    /**
     * 生成电费用订单
     * @param houseId
     */
    @Transactional
    @Override
    public Boolean generateElectricOrder(Long houseId) {
        Float quantity = expenseMapper.selectElectricity(houseId);
        if(quantity == null){
            return false;
        }
        Setting setting = settingMapper.selectById(2L);
        if (StringUtils.isNull(setting)){
            return false;
        }
        Expense expense = generateExpense(quantity, houseId, setting);
        Long id = expenseMapper.insertExpense(expense);
        return true;
    }

    /**
     * 生成电费用订单
     * @param houseId
     */
    @Transactional
    @Override
    public Boolean generatePropertyOrder(Long houseId) {
        Setting setting = settingMapper.selectById(3L);
        if (StringUtils.isNull(setting)){
            return false;
        }
        Expense expense = generateExpense(1.00F, houseId, setting);
        Long id = expenseMapper.insertExpense(expense);
        return true;
    }




    //为费用实体赋值
    public Expense generateExpense(Float quantity,Long houseId,Setting setting){
        Owner owner = expenseMapper.selectOwnerByHouseId(houseId);
        Expense expense = new Expense();
        BigDecimal totalPrice = setting.getUnitPrice().multiply(BigDecimal.valueOf(quantity));
        expense.setOwnerId(owner.getId());
        expense.setHouseNum(owner.getHouseNum());
        expense.setOwnerName(owner.getName());
        expense.setId(SnowFlakeUtil.generateIdNum());
        expense.setUnitPrice(setting.getUnitPrice());
        expense.setHouseId(houseId);
        expense.setState(0);
        expense.setUnit(setting.getUnit());
        expense.setQuantity(quantity);
        expense.setTotalPrice(totalPrice);
        expense.setTypeName(setting.getName());
        expense.setDelState(0);
        return expense;
    }


}
