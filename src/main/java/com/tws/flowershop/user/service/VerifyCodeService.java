package com.tws.flowershop.user.service;

import com.tws.flowershop.user.service.impl.VerifyCodeImpl;
import com.tws.flowershop.comment.utils.DateTimeUtil;
import com.tws.flowershop.comment.utils.VerifyCodeUtil;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author TaoWeiShu
 * @date: 2023/10/8  15:07
 */
@Service
public class VerifyCodeService  {

    @Resource
    private VerifyCodeUtil verifyCodeUtil;

    @Resource
    private VerifyCodeImpl verifyCodeImpl;

    @Resource
    private DateTimeUtil dateTimeUtil;

    /**
     * 是否允许发送
     *
     * @param isCode 验证码成功与否
     */
    public Map<String, Object> allowSend(boolean isCode) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (isCode) {
            //允许发送
            verifyCodeImpl.setData("loading");
            verifyCodeImpl.setType("email");
            verifyCodeImpl.setMsg("allow");
            hashMap.put("success", true);
        } else {
            verifyCodeImpl.setData("loading error");
            verifyCodeImpl.setMsg("图片验证码未正确校验");
            verifyCodeImpl.setType("email");
            hashMap.put("success", false);
        }
        hashMap.put("data", verifyCodeImpl);
        return hashMap;
    }

    /**
     * @param email 邮箱地址
     * @return 验证码模版对象
     */
    public VerifyCodeImpl sendCode(String email,HttpServletRequest request) {
        //首先校验邮箱
        String regex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9-]+\\.)+[A-Za-z0-9]{2,4}$";
        //校验邮箱
        boolean isValid = Pattern.matches(regex, email);
        HttpSession session = request.getSession();
        try {
            if (isValid) {
                String code = verifyCodeUtil.sendVerifyCode(email);
                if (code!=null) {
                    verifyCodeImpl.setData(code);
                    verifyCodeImpl.setMsg("success");
                    verifyCodeImpl.setType("email");
                    session.setAttribute("isReg",true);
                    return verifyCodeImpl;
                } else {
                    verifyCodeImpl.setData(null);
                    verifyCodeImpl.setMsg("messagingException");
                    session.setAttribute("isReg",false);
                    verifyCodeImpl.setType("email");
                }
            }
            verifyCodeImpl.setData(null);
            verifyCodeImpl.setMsg("邮箱格式不正确");
            session.setAttribute("isReg",false);
            verifyCodeImpl.setType("email");
            return verifyCodeImpl;
        } catch (MessagingException messagingException) {
            verifyCodeImpl.setData(null);
            verifyCodeImpl.setMsg("messagingException");
            session.setAttribute("isReg",false);
            verifyCodeImpl.setType("email");
        }
        return verifyCodeImpl;
    }

}
