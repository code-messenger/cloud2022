package cool.yunlong.cloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Hystrix监控程序启动类
 *
 * @author yunlong
 * @since 2022/4/23 23:41
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboard {

    public static void main(String[] args) {

        SpringApplication.run(HystrixDashboard.class, args);
    }
}
