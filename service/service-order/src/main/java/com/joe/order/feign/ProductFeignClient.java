package com.joe.order.feign;


import com.joe.common.allenum.ServerEnum;
import com.joe.order.fallback.ProductFeignClientFallback;
import com.joe.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 乔没乔见
 * @version 1.0
 * @email joe_cq@163.com
 * @created 2025/6/11
 * @Description
 */
@FeignClient(value = "service-product", fallback = ProductFeignClientFallback.class)
public interface ProductFeignClient {

    //mvc注解的两套使用逻辑
    //1.标注在Controller类上，是接受这样的请求
    //2.标注在FeignClient类上，是调用这样的请求
    @GetMapping("/product/product/{id}")
    Product getProductById(@PathVariable("id") Long id);
}
