package com.tws.flowershop.comment.utils;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author TaoWeiShu
 * @date: 2023/10/18  15:14
 */
@Component
public class UpLoadUtil {
    @Resource
    private FileOptionUtil fileOptionUtil;

    public String uploadProductImage(MultipartFile file, String id) {
        if (!file.isEmpty()) {
            try {
                // 获取上传文件的名称
                String fileName = file.getOriginalFilename();
                System.out.println("文件名是" + fileName);
                //获取文件类型
                String fileContentType = file.getContentType();
                if (!("image/jpeg".equals(fileContentType) || "image/png".equals(fileContentType))) {
                    return null;
                }
                System.out.println("文件的类型是" + fileContentType);
                String fileType = fileOptionUtil.getFileType(fileName);
                if (fileType == null) {
                    return null;
                }
                // 指定上传目录，确保目录存在
                String uploadDirectory = "E:/fs-upload/images/";
                File uploadPath = new File(uploadDirectory);
                if (!uploadPath.exists()) {
                    uploadPath.mkdirs();
                }
                String fsUserPath = id + ".jpg" + "220x240." + fileType;
                System.out.println("文件路径和文件名显示" + fsUserPath);
                // 创建文件路径
                File destFile = new File(uploadPath.getAbsolutePath() + File.separator + fsUserPath);
                // 保存文件
                file.transferTo(destFile);
                //存贮路径至 nginx 服务器

                return "/images/" + fsUserPath;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}