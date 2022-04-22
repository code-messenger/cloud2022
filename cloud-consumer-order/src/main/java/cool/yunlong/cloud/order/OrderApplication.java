package cool.yunlong.cloud.order;

import cool.yunlong.cloud.configuration.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * 订单服务启动类
 *
 * @author yunlong
 * @since 2022/4/22 15:54
 */
@EnableEurekaClient
// 自定义负载均衡策略
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = RibbonConfiguration.class)
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {

        SpringApplication.run(OrderApplication.class, args);
    }
}
