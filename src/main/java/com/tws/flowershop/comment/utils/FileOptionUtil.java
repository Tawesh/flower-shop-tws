package com.tws.flowershop.comment.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author TaoWeiShu
 * @date: 2023/9/7  9:17
 */
@Component
public class FileOptionUtil {

    /**
     * 获取文件的后缀
     *
     * @param fileEnd 文件名
     * @return 文件后缀名
     */
    public String getFileType(String fileEnd) {

        // 正则表达式匹配文件后缀
        Pattern pattern1 = Pattern.compile("image/(\\w+)");

        // 正则表达式匹配文件后缀
        Pattern pattern2 = Pattern.compile("\\.([^.]+)$");
        Matcher matcher2 = pattern2.matcher(fileEnd);

        Matcher matcher1 = pattern1.matcher(fileEnd);

        if (matcher1.find()) {
            return matcher1.group(1);
        } else if (matcher2.find()) {
            return matcher2.group(1);
        } else {
            System.out.println("没有找到文件后缀");
            return null;
        }
    }

}
