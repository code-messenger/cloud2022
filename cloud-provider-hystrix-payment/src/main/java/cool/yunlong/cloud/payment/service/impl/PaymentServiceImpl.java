package cool.yunlong.cloud.payment.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import cool.yunlong.cloud.payment.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author yunlong
 * @since 2022/4/23 19:01
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    /**
     * 模拟正常调用
     *
     * @param id 订单id
     * @return 返回结果
     */
    @Override
    public String paymentInfo_OK(Integer id) {
        return "调用成功：" + "paymentInfo_ok" + "\t" + "线程池：" + Thread.currentThread().getName();
    }

    /**
     * 服务降级
     * 当调用超时或者异常时，执行降级方法
     * 调用服务的时间大于等于配置的时间，则触发降级
     *
     * @param id 订单id
     * @return 返回结果
     */
    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOut_handle", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "7000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        // 模拟超时
        int timeNumber = 6;

        // 模拟异常
//        int a = 10 / 0;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "调用成功：" + "paymentInfo_TimeOut" + "\t" + "线程池：" + Thread.currentThread().getName();
    }

    /**
     * 降级方法 当调用超时或者异常时，执行降级方法
     * 作用域 、参数列表、返回类型要一致 方法名称随意
     *
     * @param id 订单id
     * @return 返回结果
     */
    public String paymentInfo_TimeOut_handle(Integer id) {
        return "调用失败，请稍后再试，o(╥﹏╥)o";
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),   //当在配置时间窗口内达到此数量，打开断路，默认20个
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),  //断路多久以后开始尝试是否恢复，默认5s
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //出错百分比阈值，当达到此阈值后，开始短路。默认50%
    })

    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("*****id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();//hutool.cn工具包

        return Thread.currentThread().getName() + "\t" + "调用成功,流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " + id;
    }
}
