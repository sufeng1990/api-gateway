package ai.shuzhi.iot.gateway.route.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author sf
 * @date 2020/4/17 14:17
 */
@Component
@FeignClient(value = "gateway-oauth" )
public interface OauthClient {
    @GetMapping("oauth/test")
    Boolean test();

}
