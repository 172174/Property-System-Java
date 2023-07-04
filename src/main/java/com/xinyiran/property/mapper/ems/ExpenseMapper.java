package com.xinyiran.property.mapper.ems;

import com.xinyiran.property.entity.ems.Expense;
import com.xinyiran.property.entity.hms.Owner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExpenseMapper {
    //查找上个月用水量
    Float selectWater(Long houseId);

    //查找上个月用气量
    Float selectGas(Long houseId);

    //查找上个月用电量
    Float selectElectricity(Long houseId);

    //插入费用订单
    Long insertExpense(Expense expense);

    //查找费用列表,分页
    List<Expense> selectListPage(Expense expense);

    //查询业主信息
    Owner selectOwnerByHouseId(Long houseId);


    //查询详情
    Expense selectDetail(Long expensId);

    List<Expense> findByHouseId(Long houseId);

    void pay(Long id);
}
