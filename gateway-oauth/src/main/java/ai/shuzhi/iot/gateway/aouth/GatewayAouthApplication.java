package ai.shuzhi.iot.gateway.aouth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 苏峰
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("ai.shuzhi.iot")
public class GatewayAouthApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayAouthApplication.class, args);
    }

}
