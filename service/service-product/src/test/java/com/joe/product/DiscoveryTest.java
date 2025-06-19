package com.joe.product;

import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import com.alibaba.nacos.api.exception.NacosException;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

/**
 * @author 乔没乔见
 * @version 1.0
 * @email joe_cq@163.com
 * @created 2025/6/8
 * @Description
 */
@SpringBootTest
public class DiscoveryTest {
    @Autowired
    private DiscoveryClient discoveryClient; // 服务发现客户端-通用
    @Autowired
    private NacosServiceDiscovery nacosServiceDiscovery; // Nacos服务发现客户端-nacos 专用

    @Test
    void nacosServiceDiscoveryTest() throws NacosException {
        for (String service : nacosServiceDiscovery.getServices()) {
            System.out.println("service = " + service);//获取服务列表（服务名）
            for (ServiceInstance instance : nacosServiceDiscovery.getInstances(service)) { //获取服务实例
                System.out.println("ip: " + instance.getHost() + "; " + "port: " + instance.getPort());
            }
        }
    }

    @Test
    public void discoveryClientTest() {
        for (String service : discoveryClient.getServices()) {
            System.out.println("service = " + service);//获取服务列表（服务名）
            for (ServiceInstance instance : discoveryClient.getInstances(service)) { //获取服务实例
                System.out.println("ip: " + instance.getHost() + "; " + "port: " + instance.getPort());
            }
        }
    }
}
