package cool.yunlong.cloud.order.controller;

import cool.yunlong.cloud.commons.entity.Payment;
import cool.yunlong.cloud.commons.entity.RestResponse;
import cool.yunlong.cloud.order.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yunlong
 * @since 2022/4/22 22:12
 */
@RestController
@RequestMapping("/consumer/payment")
public class OrderFeignController {

    // 注入代理对象
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/{id}")
    public RestResponse<Payment> getById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);  // 通过 OpenFeign 接口进行远程调用 面向接口调用
    }

}
