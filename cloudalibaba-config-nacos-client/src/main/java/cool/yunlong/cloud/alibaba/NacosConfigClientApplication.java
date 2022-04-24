package cool.yunlong.cloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yunlong
 * @since 2022/4/24 17:40
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosConfigClientApplication {

    public static void main(String[] args) {

        SpringApplication.run(NacosConfigClientApplication.class, args);
    }
}
