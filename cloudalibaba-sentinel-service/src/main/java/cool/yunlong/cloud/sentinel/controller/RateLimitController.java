package cool.yunlong.cloud.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import cool.yunlong.cloud.commons.entity.Payment;
import cool.yunlong.cloud.commons.entity.RestResponse;
import cool.yunlong.cloud.sentinel.service.CustomerBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自定义限流规则
 *
 * @author yunlong
 * @since 2022/4/26 16:17
 */
@RestController
@Slf4j
public class RateLimitController {

    /**
     * 1、按照Url地址配置规则 默认降级处理消息：Blocked by Sentinel (flow limiting) 不友好
     * 2、按照资源名称配置规则，走 blockHandle 降级方法 返回自定义消息 友好
     * 3、引发的问题 代码膨胀问题，controller中既包含请求处理，又包含自定义的降级处理
     * 如何解决?
     * 业务分开 单独配置一个自定义降级规则类  blockHandlerClass = CustomerBlockHandler.class
     * 自定义降级处理方法的访问修饰符、返回结果类型要与目标方法一致 参数列表要在最后加上BlockException类型的参数 方法名称任意
     */
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException", blockHandlerClass = CustomerBlockHandler.class)
    public RestResponse byResource() {
        return new RestResponse(200, "按资源名称限流测试OK", new Payment(2020L, "serial001"));
    }

//    /**
//     * 自定义降级处理规则
//     */
//    public RestResponse handleException(BlockException exception) {
//        return new RestResponse<>(444, exception.getClass().getCanonicalName() + "\t 服务不可用");
//    }

    /**
     * @SentinelResource 与 Hystrix 组件中的@HystrixCommand注解作用类似的。
     * value = "byResourceName"  用于设置资源名称，只有根据资源名称设置的规则，才能执行blockHandler所引用降级方法。
     * 如果按照映射路径进行规则配置，返回默认降级消息：Blocked by Sentinel (flow limiting)
     * blockHandler 配置违规 匹配这个方法
     * fallback 自身出现异常 代码异常 匹配这个方法
     * blockHandlerClass 用于引用降级方法的处理器类。注意：降级方法必须是static的。否则，无法解析
     * blockHandler + blockHandlerClass 只处理配置违规，进行降级处理。代码出现异常，不执行的。
     * <p>
     * blockHandler + fallback 同时存在，配置违规，代码也有异常，这时，走blockHandler配置文件降级处理
     * <p>
     * exceptionsToIgnore 设置特定异常不需要降级处理。但还是会触发配置违规方法
     */
    @RequestMapping("/fallback/{id}")
    @SentinelResource(value = "byFallbackName",
            blockHandler = "handleException3", blockHandlerClass = CustomerBlockHandler.class,
            fallback = "fallbackException", fallbackClass = CustomerBlockHandler.class,
            exceptionsToIgnore = IllegalArgumentException.class
    )
    public RestResponse fallback(@PathVariable("id") Long id) {
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常....");
        }

        if (id == -1) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录,空指针异常");
        }
        RestResponse result = new RestResponse<>(200, "数据已经获取", new Payment(id, "test" + 1));
        return result;
    }
}
