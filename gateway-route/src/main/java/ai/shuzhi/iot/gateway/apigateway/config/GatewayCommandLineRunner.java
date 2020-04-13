package ai.shuzhi.iot.gateway.apigateway.config;

import ai.shuzhi.iot.gateway.apigateway.biz.RouteBiz;
import ai.shuzhi.iot.gateway.apigateway.biz.TestBiz;
import ai.shuzhi.iot.gateway.apigateway.entity.Route;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 苏峰
 * <p>
 * 启动初始化数据库中路由配置到redis缓存中
 */
@Component
@Log4j2
public class GatewayCommandLineRunner implements CommandLineRunner {

    @Autowired
    private RouteBiz routeBiz;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 初始化路由信息到redis
     *
     * @param args 参数
     * @throws Exception 异常
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("初始化路由配置到Redis");
        log.info("路由信息----->" + routeBiz.selectAll());
        List<Route> routes = routeBiz.selectAll();
        routes.forEach(rout -> {
            redisTemplate.opsForHash().put("ROUTE_KEY", rout.getId(), rout);
        });
    }
}
