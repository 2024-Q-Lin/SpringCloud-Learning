package com.joe.common.allenum;


import lombok.Getter;

/**
 * @author 乔没乔见
 * @version 1.0
 * @email joe_cq@163.com
 * @created 2025/6/9
 * @Description
 */
@Getter
public enum ServerEnum {
    ORDER_SERVER(1,"service-order", "订单服务"),
    PRODUCT_SERVER(2,"service-product", "商品服务");

    private final Integer serverCode;
    private final String serverName;
    private final String serverDesc;

    ServerEnum(Integer serverCode, String serverName,String serverDesc) {
        this.serverCode = serverCode;
        this.serverName = serverName;
        this.serverDesc = serverDesc;
    }
}
