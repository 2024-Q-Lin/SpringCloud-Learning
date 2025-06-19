package com.joe.product.controller;


import com.joe.product.bean.Product;
import com.joe.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 乔没乔见
 * @version 1.0
 * @email joe_cq@163.com
 * @created 2025/6/9
 * @Description
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查询商品信息
     * @param productId 商品 ID
     * @return 商品信息
     */
    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable("productId") Long productId,
                              HttpServletRequest request) {
        String header = request.getHeader("X-Token");
        log.info("调用了当前端口,token="+header);
        return productService.getProductById(productId);
    }
}
