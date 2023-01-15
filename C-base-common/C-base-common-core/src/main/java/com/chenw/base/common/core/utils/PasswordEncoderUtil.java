package com.chenw.base.common.core.utils;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

import java.nio.charset.StandardCharsets;

/**
 * 密码工具
 * @author chenw
 */
public class PasswordEncoderUtil {

    /**
     * 生成随机盐
     * @return
     * @throws Exception
     */
    public static String generateSalt()   {
        return UUID.randomUUID().toString().replace("-","").substring(0,16);
    }

    /**
     * 加密
     * @param password - 原始密码
     * @param salt - 随机盐
     * @return
     */
    public static String generatePassword(String password,String salt) {
        AES aes = SecureUtil.aes(salt.getBytes(StandardCharsets.UTF_8));
        return aes.encryptHex(password.trim());
    }

    /**
     * 解密
     * @param salt - 随机盐
     * @param password - 加密密码
     * @return
     */
    public static String decryptPassword(String  salt,String password){
        AES aes = SecureUtil.aes(salt.getBytes(StandardCharsets.UTF_8));
        return aes.decryptStr(password.trim(), CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 校验密码
     * @param loginPassword - 登录密码
     * @param userPassword - 用户密码
     * @return
     */
    public static Boolean validPassword(String loginPassword,String userPassword,String salt){
        AES aes = SecureUtil.aes(salt.getBytes(StandardCharsets.UTF_8));
        return aes.decryptStr(userPassword).equals(loginPassword.trim());
    }



}
