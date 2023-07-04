package com.xinyiran.property.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@ConfigurationProperties(prefix = "xinyiran.jwt")
public class JwtUtil {

//    header: Authorzation
//    expire: 604800 #7天
//    secret: wotmd8a4dw648w8d45a5w8w4s56a58wm
    private long expire = 604800;//自定义过期时间
    private String secret = "wotmd8a4dw648w8d45a5w8w4s56a58wm";//密钥
    private String header = "Authorzation";

    //生成jwt
    public String createToken(String username){
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime()+ 1000 *expire);
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(username)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }
    //解析jwt
    public Claims getClaimsByToken(String jwt){
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt)
                    .getBody();
        }catch (Exception e){
            return null;
        }
    }

    //jwt是否过期
    public boolean isTokenExpired(Claims claims){
        return claims.getExpiration().before(new Date());
    }
}
