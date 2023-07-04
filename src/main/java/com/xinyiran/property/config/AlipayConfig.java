package com.xinyiran.property.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 配置文件读取
 *
 */
@Configuration
@Data
@Component
public class AlipayConfig {

    /**
     * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */
    private String appId = "xxxxxxxxxxxxxxxxxxxxxxxx";

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    private String privateKey = "xxxxxxxxxxxxxxxxxxxxxxxx";

    /**
     * 支付宝公钥,
     */
    private String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA29RBlR2PcLo4x9cZYaXj1xaiUV0dhgJptFOOeKDcOk0YVlJXQBMa0sUKlEUF9DARAv1FVqbbTBP+TvlbQpmSHdzeIcdZCDLl2AJooNipBahDoSrE+EFkVloagz48rcr01l6mSF6c4kNwMgZIZST39raHEnxWuYetIBESHZF6QeNOMO4rkJba8pCvgFfeXXT6RBT71JTgvBgG14JWivcXesyrsqeH4B/7VbKnfFZFYlxB328x2qmFrlXfc2fqF8ipwoP0ZEfJICrg2uuh5TJ2ogxCD8nV7TH18dX8yNA0fB6zCXQEA7PKlNA0PpyvAgUKt7D7gq01JG327/7dynZgjQIDAQAB";

    /**
     * 服务器异步通知页面路径需http://格式的完整路径，不能加?id=123这类自定义参数
     */
    private String notifyUrl = "http://2v7209710c.yicp.fun:50270/notify";

    /**
     * 页面跳转同步通知页面路径 需http://格式的完整路径.
     * 支付完成后返回的地址
     */
    private String returnUrl = "http://localhost:8080/#/hms/house";

    /**
     * 签名方式
     */
    private String signType = "RSA2";

    /**
     * 字符编码格式
     */
    private String charset = "utf-8";

    /**
     * 支付宝网关
     */
    private String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    /**
     * 支付宝网关
     */
    private String logPath = "C:\\";
}