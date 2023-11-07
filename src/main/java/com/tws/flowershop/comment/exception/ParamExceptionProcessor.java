package com.tws.flowershop.comment.exception;

import com.tws.flowershop.comment.entity.Message;
import jakarta.annotation.Resource;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 参数异常处理器
 *
 * @author TaoWeiShu
 * @date: 2023/9/15  9:37
 */
@RestControllerAdvice
public class ParamExceptionProcessor {

    /**
     * 参数传递不正确
     */

    @Resource
    private Message message;

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Message handleMissingParameter(Exception ex) {
        message.setCode(-1);
        message.setMsg("Request failed because necessary parameters are missing");
        return message;
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Message httpMessageNotReadableException(Exception ex) {
        message.setCode(-1);
        message.setMsg("Request failed because necessary parameters are missing");
        return message;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String httpRequestMethodNotSupportedException(Exception ex) {
        return "This request method is not supported!";
    }

}
