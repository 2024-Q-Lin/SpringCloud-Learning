package com.joe.order.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 乔没乔见
 * @version 1.0
 * @email joe_cq@163.com
 * @created 2025/6/11
 * @Description
 */
@FeignClient(value = "weather-client",url = "http://alive18.data.moji.com")
public interface weatherFeignClient {

    @PostMapping("/whapi/json/alicityweather/condition")
    String getWeather(@RequestHeader("Authorization") String authorization,
                      @RequestParam("token") String token,
                      @RequestParam("cityId") String cityId);
}
