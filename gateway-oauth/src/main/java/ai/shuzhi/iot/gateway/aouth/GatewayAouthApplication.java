package ai.shuzhi.iot.gateway.aouth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 *
 * @author 苏峰
 */
@EnableAuthorizationServer
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayAouthApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayAouthApplication.class, args);
    }

}