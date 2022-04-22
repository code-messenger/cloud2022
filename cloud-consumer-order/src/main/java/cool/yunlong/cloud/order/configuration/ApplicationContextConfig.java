package cool.yunlong.cloud.order.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author yunlong
 * @since 2022/4/22 16:01
 */
@Configuration
public class ApplicationContextConfig {

    /**
     * 创建RestTemplate   RestTemplate是一个基于Restful的网络请求工具类
     * RestTemplate (remote call) + Ribbon (load balance)  远程调用 + 负载均衡
     *
     * @return RestTemplate
     */
    @Bean
    @LoadBalanced
    public RestTemplate RestTemplate() {
        return new RestTemplate();
    }
}
