package ai.shuzhi.iot.gateway.route.filter;

import ai.shuzhi.iot.gateway.route.client.OauthClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

/**
 * @author sf
 * @date 2020/4/16 16:20
 */
@Configuration
@Slf4j
public class AccessGatewayFilter implements GlobalFilter, Ordered {

    @Autowired
    private OauthClient oauthClient;

    private static final String X_CLIENT_TOKEN_USER = "x-client-token-user";
    private static final String X_CLIENT_TOKEN = "x-client-token";

    /**
     * 不需要网关签权的url配置(/oauth,/open)
     * 默认/oauth开头是不需要的
     */
    @Value("${gate.ignore.authentication.startWith}")
    private String ignoreUrls = "/oauth";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String authentication = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String method = request.getMethodValue();
        String url = request.getPath().value();
        log.debug("url:{},method:{},headers:{}", url, method, request.getHeaders());
        //不需要网关签权的url
        if (this.ignoreAuthentication(url)) {
            return chain.filter(exchange);
        }
        //调用签权服务看用户是否有权限，若有权限进入下一个filter
        if (oauthClient.test()) {
            ServerHttpRequest.Builder builder = request.mutate();
            //TODO 转发的请求都加上服务间认证token
            builder.header(X_CLIENT_TOKEN, "TODO zhoutaoo添加服务间简单认证");
            //将jwt token中的用户信息传给服务
            builder.header(X_CLIENT_TOKEN_USER, getUserToken(authentication));
            return chain.filter(exchange.mutate().request(builder.build()).build());
        }
        return unauthorized(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    /**
     * 提取jwt token中的数据，转为json
     *
     * @param authentication
     * @return
     */
    private String getUserToken(String authentication) {
        String token = "{}";
//        try {
//            token = new ObjectMapper().writeValueAsString(authService.getJwt(authentication).getBody());
//            return token;
//        } catch (JsonProcessingException e) {
//            log.error("token json error:{}", e.getMessage());
//        }
        return token;
    }

    /**
     * 网关拒绝，返回401
     *
     * @param
     */
    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = serverWebExchange.getResponse()
                .bufferFactory().wrap(HttpStatus.UNAUTHORIZED.getReasonPhrase().getBytes());
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

    public boolean ignoreAuthentication(String url) {
        return Stream.of(this.ignoreUrls.split(",")).anyMatch(ignoreUrl -> url.startsWith(StringUtils.trim(ignoreUrl)));
    }
}
