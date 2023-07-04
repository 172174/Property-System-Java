package com.xinyiran.property.controller;

import com.alipay.api.AlipayApiException;
import com.xinyiran.property.common.Result;
import com.xinyiran.property.entity.dto.Alipay;
import com.xinyiran.property.mapper.ems.ExpenseMapper;
import com.xinyiran.property.service.oss.AliOssService;
import com.xinyiran.property.service.pay.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PayController {
    @Autowired
    private AlipayService alipayService;

    @Autowired
    private AliOssService aliOssService;

    @Autowired
    private ExpenseMapper expenseMapper;

    /**
     * 阿里支付
     */
    @PostMapping("/alipay")
    public Result alipay(@RequestBody() Alipay alipay) throws AlipayApiException {
        String str = alipayService.aliPay(alipay);
        return Result.succ(str);
    }

    /**
     * 获得上传签证
     */
    @GetMapping("/upload")
    public Result upload(){
        System.out.println("访问了获取上传签证");
        return Result.succ(aliOssService.getPolicy());
    }


    @PostMapping("/notify")  // 注意这里必须是POST接口
    public String payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
                // System.out.println(name + " = " + request.getParameter(name));
            }


            // 支付宝验签
//            if (Factory.Payment.Common().verifyNotify(params)) {
//                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));
//
//                // 更新订单未已支付
//                ordersMapper.updateState(tradeNo, "已支付", gmtPayment, alipayTradeNo);
//            }
        }
        return "success";
    }
}
