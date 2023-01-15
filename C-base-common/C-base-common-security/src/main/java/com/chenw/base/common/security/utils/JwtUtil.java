package com.chenw.base.common.security.utils;

import cn.hutool.core.convert.Convert;
import com.chenw.base.common.core.constant.SecurityConstants;
import com.chenw.base.common.core.constant.TokenConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName JwtUtil
 * @Description:  JWT工具类
 * @Author chenw
 * @Date 2022/9/21
 * @Version
**/
public class JwtUtil {

    private static final String secret = TokenConstants.SECRET;

    /**
     * 生成不可过期令牌
     * @param claims 令牌数据
     * @return java.lang.String 令牌
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * 生成可过期的令牌
     * @param claims 令牌数据
     * @return java.lang.String 令牌
     */
    public static String generateExpirableToken(Map<String, Object> claims) {
        //存放userId,生成时间,过期时间,加密规则
        return Jwts.builder().setClaims(claims).setExpiration(generatorExpirationData()).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * 从令牌中获取声明数据
     * @param token 令牌
     * @return io.jsonwebtoken.Claims 令牌数据
     */
    public static Claims parseToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * 生成过期数据
     * @return 时间
     */
    private static Date generatorExpirationData() {
        return new Date(System.currentTimeMillis() + 60*60*24*7*1000);
    }

    /**
     * 判断 令牌 是否过期
     * @param token 令牌
     * @return boolean true/是,false/否
     */
    public static boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 获取令牌中的过期时间
     * @param token 令牌
     * @return java.util.Date 时间
     */
    private static Date getExpiredDateFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.getExpiration();
    }

    /**
     * 根据令牌获取用户ID
     * @param token 令牌
     * @return java.lang.String 用户ID
     */
    public static String getUserId(String token){
        Claims claims = parseToken(token);
        return getValue(claims, SecurityConstants.DETAILS_USER_ID);
    }

    /**
     * 根据令牌获取用户名称
     * @param token 令牌
     * @return java.lang.String 用户名称
     */
    public static String getUserName(String token){
        Claims claims = parseToken(token);
        return getValue(claims, SecurityConstants.DETAILS_USERNAME);
    }

    /**
     * 根据身份信息获取键值
     *
     * @param claims 身份信息
     * @param key 键
     * @return 值
     */
    public static String getValue(Claims claims, String key)
    {
        return Convert.toStr(claims.get(key), "");
    }

}