package cool.yunlong.cloud.sentinel.service;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import cool.yunlong.cloud.commons.entity.RestResponse;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 自定义降级规则处理类
 *
 * @author yunlong
 * @since 2022/4/26 16:27
 */
public class CustomerBlockHandler {

    /**
     * 自定义规则处理类中的所有方法必须是 static 修饰的
     */
    public static RestResponse handleException(BlockException exception) {
        return new RestResponse(2020, "自定义限流处理信息.... CustomerBlockHandler --- 1");
    }

    public static RestResponse handleException2(BlockException exception) {
        return new RestResponse(2020, "自定义限流处理信息.... CustomerBlockHandler --- 2");
    }

    public static RestResponse handleException3(@PathVariable("id") Long id, BlockException exception) {
        return new RestResponse(444, exception.getClass().getCanonicalName() + "自定义配置违规处理规则");
    }

    public static RestResponse fallbackException(@PathVariable("id") Long id, Throwable exception) {
        return new RestResponse(432, exception.getClass().getCanonicalName() + "自定义代码异常处理规则");
    }
}
