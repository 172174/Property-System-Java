package com.xinyiran.property.controller.ems;

import com.github.pagehelper.PageInfo;
import com.xinyiran.property.common.Result;
import com.xinyiran.property.controller.BaseController;
import com.xinyiran.property.entity.ems.Expense;
import com.xinyiran.property.service.ems.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.PanelUI;
import java.util.List;

@RestController
public class ExpenseController extends BaseController {
    @Autowired
    private ExpenseService expenseService;

    /**
     * 生成所有费用订单
     * @param houseId
     * @return
     */
    @GetMapping("/admin/expense/insert/{houseId}")
    public Result insert(@PathVariable("houseId")Long houseId){
        Boolean isTrue = expenseService.generateOrder(houseId);
        return Result.succ(isTrue);
    }


    /**
     * 获取费用订单列表，分页
     * @param expense
     * @return
     */
    @GetMapping("/admin/expens/listPage")
    public Result listPage(Expense expense){
        startPage();
        List<Expense> list = expenseService.getListPage(expense);
        PageInfo<Expense> pageInfo = new PageInfo<>(list);
        return Result.succ(pageInfo);
    }

    /**
     * 获取订单详情
     * @param expensId
     * @return
     */
    @GetMapping("/admin/ems/expens/detail/{id}")
    public Result detail(@PathVariable("id")Long expensId){
        return Result.succ(expenseService.getDetail(expensId));
    }


    /**
     *
     * @param expensId
     * @return
     */
    @GetMapping("/admin/ems/expens/pay/{id}")
    public Result pay(@PathVariable("id")Long expensId){
        Boolean is = expenseService.pay(expensId);
        return Result.succ(null);
    }

}
