package com.joe.product.service;


import com.joe.product.bean.Product;
import org.springframework.stereotype.Service;

/**
 * @author 乔没乔见
 * @version 1.0
 * @email joe_cq@163.com
 * @created 2025/6/9
 * @Description
 */
public interface ProductService {
    /**
     * 根据商品id查询商品信息
     * @param productId 商品id
     * @return 商品信息
     */
    Product getProductById(Long productId);
}
