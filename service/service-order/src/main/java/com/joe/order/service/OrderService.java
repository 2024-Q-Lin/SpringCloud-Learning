package com.joe.order.service;


import com.joe.order.bean.Order;
import org.springframework.stereotype.Service;

/**
 * @author 乔没乔见
 * @version 1.0
 * @email joe_cq@163.com
 * @created 2025/6/9
 * @Description
 */
public interface OrderService {
    /**
     * 创建订单
     * @param productId 产品 ID
     * @param userId 用户 ID
     * @return 订单信息
     */
    Order createOrder(Long productId, Long userId);
}
