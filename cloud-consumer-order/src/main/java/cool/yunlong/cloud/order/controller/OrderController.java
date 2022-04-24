package cool.yunlong.cloud.order.controller;

import cool.yunlong.cloud.commons.entity.Payment;
import cool.yunlong.cloud.commons.entity.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * 订单接口
 *
 * @author yunlong
 * @since 2022/4/22 16:03
 */
@RestController
@RequestMapping("/consumer/payment")
public class OrderController {

    // 通过服务名称到注册中心上拉取服务
    public static final String PATH = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 浏览器调用此接口，会被转发到服务提供者的接口
     *
     * @return 返回订单支付结果
     */
    @PostMapping("/create")
    public RestResponse create(@RequestBody Payment payment) {

        return restTemplate.postForObject(PATH + "/payment/create", payment, RestResponse.class);
    }

    @GetMapping("/{id}")
    public RestResponse getPaymentById(@PathVariable("id") Long id) {

        return restTemplate.getForObject(PATH + "/payment/" + id, RestResponse.class);
    }

    //==> zipkin+sleuth
    @GetMapping("/zipkin")
    public String paymentZipkin() {
        return restTemplate.getForObject("http://localhost:8001" + "/payment/zipkin/", String.class);
    }
}



