package com.tws.flowershop.comment.utils;

import com.tws.flowershop.user.service.impl.VerifyCodeImpl;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * @Author:Tws
 * @Date:2023/8/16-11:53
 */
@Component
public class VerifyCodeUtil {

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private EmailEngine emailEngine;

    @Resource
    private RandomNumberUtil randomNumberUtil;

    @Resource
    private VerifyCodeImpl verifyCodeImpl;

    /**
     *
     * 向邮箱发送验证码
     * @return 验证码
     */
    public String sendVerifyCode(String email) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //返回给前端的code-json module
            // 设置邮件发送者
            helper.setFrom(from);
            // 设置邮件接收者
            helper.setTo(email);
            // 设置邮件的主题
            helper.setSubject("注册验证码");
            // 设置邮件的正文
            String code = randomNumberUtil.generateRandomCode(6);

            String text = emailEngine.buildContent(code);
            helper.setText(text, true);
            // 发送邮件
            try {
                javaMailSender.send(message);
                return code;
            } catch (MailException e) {
                e.printStackTrace();
                return null;

            }

        }





}
