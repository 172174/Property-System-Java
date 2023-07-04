package com.xinyiran.property.service.sys.impl;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.io.FastByteArrayOutputStream;
import com.google.code.kaptcha.Producer;
import com.xinyiran.property.common.Const;
import com.xinyiran.property.common.Result;
import com.xinyiran.property.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
public class CaptchaService  {

    // 对应CaptchaConfig 定义的bean name
    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    RedisUtil redisUtil;


    /**
     * 生成图片验证码
     */
    public Result getPictureCaptcha(HttpServletResponse response) throws IOException {
        String str = "data:image.jpeg;base64,";
        String uuid = UUID.randomUUID().toString();
        String capStr = null, code = null;
        BufferedImage image = null;
        // 生成验证码
        String capText = captchaProducerMath.createText();
        //截取结果
        capStr = capText.substring(0, capText.lastIndexOf("@"));
        code = capText.substring(capText.lastIndexOf("@") + 1);
        image = captchaProducerMath.createImage(capStr);
        //结果存入redis,key为uuid 超时时间5分钟
        redisUtil.set(Const.CAPTCHA_KEY+uuid, code, 2 * 60); // 5分钟
        // 转换流信息写出
        FastByteArrayOutputStream outputStream= new FastByteArrayOutputStream();
        Map<String, Object> map = new HashMap<>();
        try {
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            return Result.fail(e.getMessage());
        }finally {
            map.put("uuid", uuid);
            map.put("img", str+Base64Encoder.encode(outputStream.toByteArray()));
            outputStream.close();
        }
        return Result.succ(map);
    }
}