package cool.yunlong.cloud.payment.service;

/**
 * @author yunlong
 * @since 2022/4/23 19:01
 */
public interface PaymentService {

    /**
     * 正常调用
     *
     * @param id id
     * @return String
     */
    String paymentInfo_OK(Integer id);

    /**
     * 超时调用
     *
     * @param id id
     * @return String
     */
    String paymentInfo_TimeOut(Integer id);

    String paymentCircuitBreaker(Integer id);
}
