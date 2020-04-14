package ai.shuzhi.iot.gateway.route.entity;

import lombok.Data;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.io.Serializable;

@Data
public class RouteDefinitionVo extends RouteDefinition implements Serializable {

    private static final long serialVersionUID = -5933028433865308097L;

    /**
     * 路由名称
     */
    private String routeName;
}
