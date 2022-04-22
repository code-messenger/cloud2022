package cool.yunlong.cloud.payment.controller;

import cool.yunlong.cloud.commons.entity.Payment;
import cool.yunlong.cloud.commons.entity.RestResponse;
import cool.yunlong.cloud.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author yunlong
 * @since 2022/4/22 0:37
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/create")
    public RestResponse<Payment> create(@RequestBody Payment payment) {
        int result = paymentService.insert(payment);
        if (result == 1) {
            return new RestResponse<>(200, "添加成功");
        }
        return new RestResponse<>(400, "添加失败");
    }

    @GetMapping("/{id}")
    public RestResponse<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.selectById(id);
        // 模拟超时 默认等待一秒钟，超过后报错
//        System.out.println("请求：" + serverPort);
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        if (payment != null) {
            return new RestResponse<>(200, "查询成功 port = " + serverPort, payment);
        }
        return new RestResponse<>(400, "查询失败");
    }
}

