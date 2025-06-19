package com.joe.order.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 乔没乔见
 * @version 1.0
 * @email joe_cq@163.com
 * @created 2025/6/10
 * @Description 专门定义配置绑定相关属性的类
 */
@Data
@Component
@ConfigurationProperties(prefix = "order") //配置批量绑定 nacos 下，可以无需 @RefreshScope 就能够实现自动刷新
public class OrderProperties {

    private String timeout;
    private String autoConfirm; //自动驼峰转换
    private String dbUrl;
}
