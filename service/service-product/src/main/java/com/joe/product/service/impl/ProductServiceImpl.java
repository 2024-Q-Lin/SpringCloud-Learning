package com.joe.product.service.impl;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;


import com.joe.product.bean.Product;
import com.joe.product.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @author 乔没乔见
 * @version 1.0
 * @email joe_cq@163.com
 * @created 2025/6/9
 * @Description
 */
@Service
public class ProductServiceImpl implements ProductService {


    /**
     * 获取商品信息
     * @param productId 商品id
     * @return 商品信息
     */
    @Override
    public Product getProductById(Long productId) {
        //创建一个固定商品对象，用于测试服务
        Product product = new Product();
        product.setId(1L);
        product.setPrice(new BigDecimal("99"));
        product.setProductName("MacBookPro M4 14英寸【24G + 512G】");
        product.setNum(3);

//        try {
//            TimeUnit.SECONDS.sleep(100);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        return product;
    }
}
