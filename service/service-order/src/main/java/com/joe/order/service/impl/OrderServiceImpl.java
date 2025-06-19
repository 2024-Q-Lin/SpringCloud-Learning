package com.joe.order.service.impl;
import java.math.BigDecimal;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.joe.common.allenum.ServerEnum;
import com.joe.order.bean.Order;
import com.joe.order.feign.ProductFeignClient;
import com.joe.order.service.OrderService;
import com.joe.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author 乔没乔见
 * @version 1.0
 * @email joe_cq@163.com
 * @created 2025/6/9
 * @Description
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private ProductFeignClient productFeignClient;

    /**
     * 创建订单
     * @param productId 产品 ID
     * @param userId 用户 ID
     * @return 订单信息
     */
    @SentinelResource(value = "createOrder")
    @Override
    public Order createOrder(Long productId, Long userId) {
//        Product product = getProductFromRemoteWithLoadBalanceAnnotation(productId);
        Product product = productFeignClient.getProductById(productId);

        Order order = new Order();
        order.setId(1L);
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setUserId(userId);
        order.setNickName("joe");
        order.setAddress("广州市天河区");
        order.setProductList(List.of(product));

        return order;
    }


    /**
     * 注解式负载均衡远程调用获取产品信息
     * @param productId 产品 ID
     * @return 产品信息
     */
    private Product getProductFromRemoteWithLoadBalanceAnnotation(Long productId) {
        String getProductUrl = "http://"+ServerEnum.PRODUCT_SERVER.getServerName()+"/product/product/"+productId;
        // log.info("getProductUrl:{}", getProductUrl);这里打印没有用，是在restTemplate.getForObject中自动装配的
        //发送请求（需要使用到restTemplate）
        Product product = restTemplate.getForObject(getProductUrl, Product.class);
        return product;
    }


    /**
     * 负载均衡远程调用获取产品信息
     * @param productId 产品 ID
     * @return 产品信息
     */
    private Product getProductFromRemoteWithLoadBalance(Long productId) {
        //负载均衡获取实例对象
        ServiceInstance instance = loadBalancerClient.choose(ServerEnum.PRODUCT_SERVER.getServerName());

        //构造远程 url（相当于在服务端发起 http 请求）
        String getProductUrl = "http://" +instance.getHost()+":"+instance.getPort()+"/product/"+"product/"+productId;
        log.info("getProductUrl:{}", getProductUrl);
        //发送请求（需要使用到restTemplate）
        Product product = restTemplate.getForObject(getProductUrl, Product.class);
        return product;
    }

    /**
     * 调用获取产品信息
     * @param productId 产品 ID
     * @return 产品信息
     */
    private Product getProductFromRemote(Long productId) {
        List<ServiceInstance> instances = discoveryClient.getInstances(ServerEnum.PRODUCT_SERVER.getServerName());

        //手动获取，直接获取第一个（用于测试，实际上需要做负载均衡）
        ServiceInstance serviceInstance = instances.get(0);
        //构造远程 url（相当于在服务端发起 http 请求）
        String getProductUrl = "http://" +serviceInstance.getHost()+":"+serviceInstance.getPort()+"/product/"+"product/"+productId;
        log.info("getProductUrl:{}", getProductUrl);
        //发送请求（需要使用到restTemplate）
        Product product = restTemplate.getForObject(getProductUrl, Product.class);
        return product;
    }
}
