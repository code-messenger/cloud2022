package cool.yunlong.cloud.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import cool.yunlong.cloud.order.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yunlong
 * @since 2022/4/23 21:20
 */
@Slf4j
@RequestMapping("/consumer/payment")
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")  //全局的fallback方法
@RestController
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("*******result:" + result);
        return result;
    }

    /**
     * 服务降级
     * <p>
     * 当调用超时或者异常时，执行降级方法
     * 调用服务的时间大于等于配置的时间，则触发降级
     * <p>
     * openFeign也有超时时间 默认1秒
     * 服务降级时以最小的超时时间为准
     *
     * @param id 订单id
     * @return String
     */
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOut_handler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @GetMapping("/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        log.info("*******result:" + result);
        return result;
    }

    // 服务降级时，执行的方法
//    public String paymentInfo_TimeOut_handler(@PathVariable("id") Integer id) {
//        return "我是消费者80，对方支付系统繁忙," + "\t" + " 请10秒钟后再试或者自己运行出错请检查自己, (┬＿┬)";
//    }

    /**
     * 全局降级处理方法 如果全局和私有的都配置了，优先执行私有的
     */
    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息，请稍后再试,(┬＿┬)";
    }
}
