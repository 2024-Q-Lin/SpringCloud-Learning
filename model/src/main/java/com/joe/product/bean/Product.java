package com.joe.product.bean;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author 乔没乔见
 * @version 1.0
 * @email joe_cq@163.com
 * @created 2025/6/9
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private Long id;
    private BigDecimal price;
    private String productName;
    private int num;
}
