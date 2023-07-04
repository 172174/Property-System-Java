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
    private String appId = "2021000120601125";

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    private String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDb1EGVHY9wujjH1xlhpePXFqJRXR2GAmm0U454oNw6TRhWUldAExrSxQqURQX0MBEC/UVWpttME/5O+VtCmZId3N4hx1kIMuXYAmig2KkFqEOhKsT4QWRWWhqDPjytyvTWXqZIXpziQ3AyBkhlJPf2tocSfFa5h60gERIdkXpB404w7iuQltrykK+AV95ddPpEFPvUlOC8GAbXglaK9xd6zKuyp4fgH/tVsqd8VkViXEHfbzHaqYWuVd9zZ+oXyKnCg/RkR8kgKuDa66HlMnaiDEIPydXtMfXx1fzI0DR8HrMJdAQDs8qU0DQ+nK8CBQq3sPuCrTUkbfbv/t3KdmCNAgMBAAECggEAc0ecHNajojoGDMaASPT1xpXYCVMLaHtbc3VeAWRaGTjUym/j0XYzWtR598c6mBOc2DR5aDeYSQ0zD7c0ZHikZ4Sr+IrR5brLTbv1omP8Pzs+PAZxxL6GH8KgpLK+n2UPgFuiV9FF9GHpNFKXJHN+laKiss8Bq8m6wFYzac4gjuvraFnvuP0LVpz8+aTL/JkBkdvzf3tT5PRktK/kDG/Y3beftAOg6K3/93u2CGCC5qri9qJ7CZ48OCpRQjvJAE8e5HiY6aE4Z2bmZG4/oocUwSEcIMRWROkmRhe9sbKNev+ItSAslwj2vVYbf+3ksjeXiGlPnm2a9vg2ftg2pkn3AQKBgQD+g9AzBNz/2/a68nRu5uOOT3vYPX/IGKXWuDk8WE55ESTOrXYpxYHOA/oviSirnLb/HCsbuzVg9TjeuwmGFjOl26oWRvY+BO7aKAhatego44og778WVSvmSARvAxE6sxt8MJEJEfAdFZiSt3fmeG6IdC2/QNyzt+qtBQQKiKvjJwKBgQDdHKFd8l1W48S243JpBFsWx5D+rZ3FcsI0lVv8qx8LeyvvSZPkr7iSGu3/yQ8BpOMpuTbYTBPNUOaWFEdsncR0gC5EWtwEITLdncIdlbxOKOSX0KoKXtJA4/5WZv7c17Gqxnd3bzhfRHwYEVLAq7OEK6uT6phNME2Ooe6COa6fKwKBgGC/GlVKc/eOVauankfJ3JYsAJeiSQB23hNQ0/DsYGgE7EjkBxa5zS3StTd8m8kgOS+gNYLFFvVUQdnonyYpKljRt0Xgk5nfpiunLAXPQADK/aane6uxlI5kscrBD7uQedIrY/ZeqzdjDiw3MYyaGuojv4AD1+E4+CvszCx052HhAoGAK+B7kRYAObbXGWfHE1ne7YVKm48+SKk+KosdBuW6MmAN1t0l8PKB0NRxOBng1XWv8l6jqIZoQ4zzRQLfLYmdK3UoOT5CfLtNybZ9h7tn86afvhSVOZuZY3EaznB/0Lkos3fQyy2R5S3E5k3BuyQXann0AVVDRcX5Df3KQ6NIqKMCgYB3dSzhABrJGLtjTRpW6456+g2inIxHuz+O4612MWrOxLYmULhgAZHkuX820tjRniRPCNpu8BshHw0ED2iovcXK2ATR/Vjs/wInU4z5miLaPaTqC2lFVMcQP/ocG6hfcnGR6zZjQq2N+TwpHKl5N8sBps7KbI4hbs1bqf3emXOW0g==";

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