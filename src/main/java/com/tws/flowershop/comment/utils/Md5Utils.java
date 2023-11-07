package com.tws.flowershop.comment.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author TaoWeiShu
 * @date: 2023/9/19  9:25
 */
@Component
public class Md5Utils {

    /**
     * @param input 需要转换的字符串
     * @return 加密后的字符串
    */
    public  String encrypt(String input) {
        try {
            // 创建MessageDigest对象并指定算法为MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 将输入字符串转换为字节数组
            byte[] inputBytes = input.getBytes();

            // 计算MD5散列值
            byte[] hashBytes = md.digest(inputBytes);

            // 将散列值转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // 处理异常
            e.printStackTrace();
            return null;
        }
    }


}
