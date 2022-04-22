package cool.yunlong.cloud.configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yunlong
 * @since 2022/4/22 21:32
 */
@Configuration
public class RibbonConfiguration {

    /**
     * 负载均衡策略 随机
     *
     * @return IRule
     */
    @Bean
    public IRule rule() {
        return new RandomRule();
    }

}
