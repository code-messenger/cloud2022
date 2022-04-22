package cool.yunlong.cloud.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yunlong
 * @since 2022/4/22 22:01
 */
@EnableEurekaClient // 开启 eureka 客户端
@EnableFeignClients // 开启 OpenFeign 远程调用功能
@SpringBootApplication
public class OrderFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderFeignApplication.class, args);
    }
}
