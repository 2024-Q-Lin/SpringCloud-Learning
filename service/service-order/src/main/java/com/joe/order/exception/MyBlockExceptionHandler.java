package com.joe.order.exception;


import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joe.common.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

/**
 * @author 乔没乔见
 * @version 1.0
 * @email joe_cq@163.com
 * @created 2025/6/19
 * @Description
 */
@Component//因为这个是在sentinel 异常抛出时自动装配生效的，所以要交给 Spring 管理
public class MyBlockExceptionHandler implements BlockExceptionHandler {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       String resourceName, BlockException e) throws Exception {
        response.setStatus(429);//429 表示请求被限流了
        response.setContentType("application/json;charset=utf-8");

        PrintWriter writer = response.getWriter();

        R error =R.error(500,resourceName+"被 Sentinel 限制了，原因是："+e.getClass().getSimpleName());
        String json = objectMapper.writeValueAsString(error);
        writer.write(json);
        writer.flush();
        writer.close();
    }
}
