package com.joe.order.interceptor;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author 乔没乔见
 * @version 1.0
 * @email joe_cq@163.com
 * @created 2025/6/19
 * @Description
 */
@Component
public class XTokenRequestInterceptor implements RequestInterceptor {

    /**
     * 请求拦截器
     * @param requestTemplate 请求模板（可以在这里设置一些前置请求信息）
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println("XTokenRequestInterceptor start...");
        requestTemplate.header("X-token", UUID.randomUUID().toString());
    }
}
