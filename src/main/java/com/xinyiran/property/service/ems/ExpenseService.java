package com.xinyiran.property.service.ems;

import com.xinyiran.property.common.Result;
import com.xinyiran.property.entity.ems.Expense;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpenseService {
    Boolean  generateOrder(Long houseId);

    Boolean generateWaterOrder(Long houseId);
    Boolean generateGasOrder(Long houseId);
    Boolean generateElectricOrder(Long houseId);

    Boolean generatePropertyOrder(Long houseId);

    List<Expense> getListPage(Expense expense);

    Expense getDetail(Long expensId);

    List<Expense> getListByHouseId(Long houseId);

    Boolean pay(Long expensId);
}
