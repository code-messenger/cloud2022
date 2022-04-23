package cool.yunlong.cloud.order.service;

import cool.yunlong.cloud.commons.entity.Payment;
import cool.yunlong.cloud.commons.entity.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 声明远程调用的接口： Feign 服务接口
 *
 * @author yunlong
 * @since 2022/4/22 22:04
 */
@Component
@FeignClient(name = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    // 声明调用的服务接口  与远程controller接口一致

    @PostMapping("/payment/create")
    RestResponse<Payment> create(@RequestBody Payment payment);

    @GetMapping("/payment/{id}")
    RestResponse<Payment> getPaymentById(@PathVariable("id") Long id);
}
