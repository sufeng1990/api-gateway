//package ai.shuzhi.iot.gateway.route.routes;
//
//import ai.shuzhi.iot.gateway.route.entity.GatewayRoute;
//import ai.shuzhi.iot.gateway.route.entity.RouteDefinitionVo;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.FilterDefinition;
//import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.net.URI;
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//@Component
//@AllArgsConstructor
//public class RedisRouteDefinitionWriter implements RouteDefinitionRepository {
//
//    @Autowired
//    private final RedisTemplate redisTemplate;
//
//    private static final String ROUTE_KEY = "gateway_routes::";
//
//    @Override
//    public Mono<Void> save(Mono<RouteDefinition> route) {
//        return route.flatMap(r -> {
//            RouteDefinitionVo vo = new RouteDefinitionVo();
//            BeanUtils.copyProperties(r, vo);
//            log.info("保存路由信息{}", vo);
//            redisTemplate.opsForHash().put(ROUTE_KEY, r.getId(), vo);
//            return Mono.empty();
//        });
//    }
//
//    @Override
//    public Mono<Void> delete(Mono<String> routeId) {
//        routeId.subscribe(id -> {
//            log.info("删除路由信息{}", id);
//            redisTemplate.opsForHash().delete(ROUTE_KEY, id);
//        });
//        return Mono.empty();
//    }
//
//    @Override
//    public Flux<RouteDefinition> getRouteDefinitions() {
//        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(GatewayRoute.class));
//        List<GatewayRoute> values = redisTemplate.opsForHash().values(ROUTE_KEY);
//        List<RouteDefinition> definitionList = new ArrayList<>();
//        ObjectMapper objectMapper = new ObjectMapper();
//        values.forEach(gatewayRoute -> {
//            RouteDefinition routeDefinition = new RouteDefinition();
//            try {
//                BeanUtils.copyProperties(gatewayRoute, routeDefinition);
//                routeDefinition.setFilters(objectMapper.readValue(gatewayRoute.getFilters(), new TypeReference<List<FilterDefinition>>() {
//                }));
//                routeDefinition.setPredicates(objectMapper.readValue(gatewayRoute.getPredicates(), new TypeReference<List<PredicateDefinition>>() {
//                }));
//                routeDefinition.setUri(new URI(gatewayRoute.getUri()));
//            }catch (Exception e){
//                log.error("网关路由对象转换失败", e);
//            }
//            definitionList.add(routeDefinition);
//        });
//        log.debug("redis 中路由定义条数： {}， {}", definitionList.size(), definitionList);
//        return Flux.fromIterable(definitionList);
//    }
//}
