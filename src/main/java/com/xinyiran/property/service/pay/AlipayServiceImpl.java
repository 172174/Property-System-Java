package com.xinyiran.property.service.pay;

import com.alibaba.fastjson2.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.xinyiran.property.config.AlipayConfig;
import com.xinyiran.property.entity.dto.Alipay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class AlipayServiceImpl implements AlipayService{

    @Autowired
    private AlipayConfig alipayConfig;

//    @Override
//    public void aliPay(HttpServletResponse response, HttpServletRequest request) throws IOException {
//        response.setContentType("text/html;charset=utf-8");
//
//        PrintWriter out = response.getWriter();
//        //获得初始化的AlipayClient
//        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGatewayUrl(), alipayConfig.getAppId(), alipayConfig.getPrivateKey() , "json", alipayConfig.getCharset(), alipayConfig.getPublicKey(), alipayConfig.getSignType());
//        //设置请求参数
//        AlipayTradePagePayRequest aliPayRequest = new AlipayTradePagePayRequest();
//        //aliPayRequest.setReturnUrl(AlipayConfig.return_url);
//        //aliPayRequest.setNotifyUrl(AlipayConfig.notify_url);
//
//        //商户订单号，后台可以写一个工具类生成一个订单号，必填
//        String order_number = new String(StringUtils.getStr());
//        //付款金额，从前台获取，必填
//        String total_amount = new String("201314");
//        //订单名称，必填
//        String subject = new String("祖万敏小可爱");
//        aliPayRequest.setBizContent("{\"out_trade_no\":\"" + order_number + "\","
//                + "\"total_amount\":\"" + total_amount + "\","
//                + "\"subject\":\"" + subject + "\","
//                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
//        //请求
//        String result = null;
//        try {
//            result = alipayClient.pageExecute(aliPayRequest).getBody();
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }
//        //输出
//        out.println(result);
//    }

    /**
     * 支付接口
     * @param alipay
     * @return
     * @throws AlipayApiException
     */
    public String aliPay(Alipay alipay) throws AlipayApiException {
        // 1、获得初始化的AlipayClient
        String serverUrl = alipayConfig.getGatewayUrl();
        String appId = alipayConfig.getAppId();
        String privateKey = alipayConfig.getPrivateKey();
        String format = "json";
        String charset = alipayConfig.getCharset();
        String alipayPublicKey = alipayConfig.getPublicKey();
        String signType = alipayConfig.getSignType();
        String returnUrl = alipayConfig.getReturnUrl();
        String notifyUrl = alipayConfig.getNotifyUrl();
        //System.out.println(appId);
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);
        // 2、设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        // 页面跳转同步通知页面路径
        alipayRequest.setReturnUrl(returnUrl);
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl(notifyUrl);
        // 封装参数
        alipayRequest.setBizContent(JSON.toJSONString(alipay));
        // 3、请求支付宝进行付款，并获取支付结果
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        // 返回付款信息
        return result;
    }
}
