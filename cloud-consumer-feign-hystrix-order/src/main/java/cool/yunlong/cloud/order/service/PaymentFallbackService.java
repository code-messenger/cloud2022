package cool.yunlong.cloud.order.service;

import org.springframework.stereotype.Component;

/**
 * 远程调用统一降级处理
 *
 * @author yunlong
 * @since 2022/4/23 22:43
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "远程调用 paymentInfo_OK 失败，服务降级处理";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "远程调用 paymentInfo_TimeOut 失败，服务降级处理";
    }
}
