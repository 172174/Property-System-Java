package com.xinyiran.property.service.pay;

import com.alipay.api.AlipayApiException;
import com.xinyiran.property.entity.dto.Alipay;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public interface AlipayService {
    /**
     * 支付宝支付调用接口
     * @param response
     * @param request
     * @throws IOException
     */
//    void  aliPay(HttpServletResponse response, HttpServletRequest request) throws IOException;

    /**
     * 支付宝支付接口
     * @param alipay
     * @return
     * @throws AlipayApiException
     */
    String aliPay(Alipay alipay) throws AlipayApiException;
}
