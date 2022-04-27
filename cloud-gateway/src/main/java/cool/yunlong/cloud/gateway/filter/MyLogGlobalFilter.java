package cool.yunlong.cloud.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * 自定义网关过滤器
 *
 * @author yunlong
 * @since 2022/4/24 15:28
 */
@Slf4j
@Component  // 声明bean对象 过滤器就会生效 无需任何配置
public class MyLogGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 请求目标执行前执行
        log.info("*********come in MyLogGateWayFilter: " + new Date());

        String username = exchange.getRequest().getQueryParams().getFirst("username");
        if (StringUtils.isEmpty(username)) {
            log.info("*****用户名为Null 非法用户,(┬＿┬)");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();    // 拒绝访问
        }
        return chain.filter(exchange);      // 放行
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
