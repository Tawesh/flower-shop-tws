package com.tws.flowershop.comment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author TaoWeiShu
 * @date: 2023/10/17  19:15
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    /**
     * 解决  No mapping for GET /favicon.ico 访问静态资源图标
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}

