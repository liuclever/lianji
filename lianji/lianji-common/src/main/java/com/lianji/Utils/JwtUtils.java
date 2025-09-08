package com.lianji.Utils;

import com.lianji.Properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * JWT 工具类
 */
@Component
public class JwtUtils {
    @Autowired
    private JwtProperties jwtProperties;

    private Key key;

    //将配置文件的 base64编码解码为数组
    @PostConstruct
    public void init(){
        //将key 赋值给 jwtProperties
        byte[] decode = Decoders.BASE64.decode(jwtProperties.getSecret());
        this.key = Keys.hmacShaKeyFor(decode);
    }

    /**
     * jwt提取用户名
     */
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * jwt提取过期时间
     */
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * 验证token是否有效
     * @param token JWT token
     * @param userName 用户名
     */
    public boolean validateToken(String token, String userName){
        String tokenUserName = extractUsername(token);
        return tokenUserName.equals(userName) && !isTokenExpired(token); // 修复逻辑错误：应该是 !isTokenExpired
    }

    /**
     * 生成token
     * @param userName 用户名
     */
    public String generateToken(String userName){
        Map<String, Object> claims = new HashMap<>(); // 修复泛型错误：应该是 Object 不是 Objects
        return createToken(claims, userName);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    /**
     * 创建token
     */
    private String createToken(Map<String, Object> claims, String subject){ // 修复泛型错误
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+jwtProperties.getExpire()))
                .signWith(key)
                .compact();
    }
}