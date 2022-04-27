package cool.yunlong.cloud.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试限流
 *
 * @author yunlong
 * @since 2022/4/26 10:14
 */
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        //int age = 10/0;
        return "------testHotKey";
    }

    /**
     * 自定义降级方法
     * 访问修饰符、返回结果类型要与目标方法一致 参数列表要在最后加上BlockException类型的参数 方法名称任意
     */
    public String deal_testHotKey(String p1, String p2, BlockException exception) {
        return "------deal_testHotKey,o(╥﹏╥)o";
    }

    @GetMapping("/testA")
    public String testA() {

        // 测试异常熔断
//        long[] num = {0, 1, 2};
//        Random random = new Random();
//        int i = random.nextInt(num.length);
//        System.out.println(5 / num[i]);

        // 测试熔断限流
//        try {
//            Thread.sleep(300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "------testB";
    }
}
