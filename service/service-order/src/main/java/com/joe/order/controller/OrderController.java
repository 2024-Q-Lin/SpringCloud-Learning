package com.joe.order.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.joe.order.bean.Order;
import com.joe.order.properties.OrderProperties;
import com.joe.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * @author 乔没乔见
 * @version 1.0
 * @email joe_cq@163.com
 * @created 2025/6/9
 * @Description
 */
@RestController
@RequestMapping("/order")
@Slf4j
//@RefreshScope // 自动刷新配置，不需要服务重启（需要配置在获取数据的类上）
public class OrderController {

    @Autowired
    private OrderService orderService;

//    @Value("${order.timeout}")
//    private String orderTimeout;
//    @Value("${order.auto-confirm}")
//    private String orderAutoConfirm;

    @Autowired
    private OrderProperties orderProperties;


    /**
     * 获取配置信息
     *
     * @return 配置信息
     */
    @GetMapping("/config")
    public String getConfig() {
        return "订单超时时间：" + orderProperties.getTimeout() + "，自动确认收货时间：" + orderProperties.getAutoConfirm() + "，数据库连接地址：" + orderProperties.getDbUrl();
    }

    //创建订单
    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") Long userId,
                             @RequestParam("productId") Long productId) {
        Order order = orderService.createOrder(productId, userId);
        return order;
    }

    //秒杀
    @GetMapping("/seckill")
    @SentinelResource(value = "seckill-order", fallback = "seckillFallback")
    public Order seckill(@RequestParam(value = "userId", required = false) Long userId,
                         @RequestParam(value = "productId", defaultValue = "1000") Long productId) {
        Order order = orderService.createOrder(productId, userId);
        return order;
    }

    public Order seckillFallback(Long userId, Long productId, Throwable e) {
        log.error("秒杀失败：{}", e.getMessage());
        Order order = new Order();
        order.setId(productId);
        order.setUserId(userId);
        order.setAddress("秒杀失败");
        return order;
    }


    //读数据
    @GetMapping("/readDb")
    public String readDb() {
        log.info("开始读数据库");
        return "readDb......";
    }

    //写数据
    @GetMapping("/writeDb")
    public String writeDb() {
        return "writeDb......";
    }
}
