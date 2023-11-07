package com.tws.flowershop.user.controler.api;

/**
 * @author TaoWeiShu
 * @date: 2023/10/8  15:42
 */

import com.tws.flowershop.comment.utils.PicVerifyCodeUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/web/tws/fs/api/v2")
public class PicVerifyCodeApi {
    private final static Logger logger = LoggerFactory.getLogger(PicVerifyCodeApi.class);

    @Resource
    private PicVerifyCodeUtil picVerifyCodeUtil;

    /**
     * 生成验证码
     */
    @GetMapping("/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            //设置相应类型,告诉浏览器输出的内容为图片
            response.setContentType("image/jpeg");
            //设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            //输出验证码图片方法
            picVerifyCodeUtil.getRandomCode(request, response);

        } catch (Exception e) {

            logger.error("获取验证码失败>>>>   ", e);

        }

    }

    /**
     * 校验验证码
     */
    @PostMapping("/checkVerify")
    public boolean checkVerify(@RequestBody Map<String,String> verifyInput, HttpSession session) {
        try {

            //从session中获取随机数
            session.setMaxInactiveInterval(60);
            String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
            if (random == null || "".equals(random) || !random.equalsIgnoreCase(verifyInput.get("verifyInput"))) {
                return false;
            } else {
                session.setAttribute("isCode",true);
                session.setAttribute("isLog",true);
                return true;
            }

        } catch (Exception e) {
            logger.error("验证码校验失败", e);
            session.setAttribute("isCode",false);
            session.setAttribute("isLog",false);
            return false;
        }
    }

}

