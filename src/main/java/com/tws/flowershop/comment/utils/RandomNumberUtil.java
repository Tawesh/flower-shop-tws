package com.tws.flowershop.comment.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Author:Tws
 * @Date:2023/8/14-19:59
 */
@Component
public class RandomNumberUtil {
    /**
     * @param length 随机数字验证码的长度
     * @return 随机验证码
    */
    public  String generateRandomCode(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length should be a positive number.");
        }
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            code.append(digit);
        }
        return code.toString();
    }
}
