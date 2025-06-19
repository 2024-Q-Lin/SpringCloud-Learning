package com.joe.order.fallback;


import com.joe.order.feign.ProductFeignClient;
import com.joe.product.bean.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author 乔没乔见
 * @version 1.0
 * @email joe_cq@163.com
 * @created 2025/6/19
 * @Description
 */
//兜底处理
@Component
public class ProductFeignClientFallback implements ProductFeignClient {
    @Override
    public Product getProductById(Long id) {
        System.out.println("服务兜底处理");
        Product product = Product.builder()
                .id(id)
                .price(new BigDecimal("0"))
                .productName("未知商品")
                .num(0)
                .build();
        return product;
    }
}
