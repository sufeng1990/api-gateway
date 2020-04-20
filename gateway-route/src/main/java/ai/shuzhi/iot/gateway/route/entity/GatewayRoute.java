package ai.shuzhi.iot.gateway.route.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;

/**
 * @author sf
 * @date 2020/4/20 14:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayRoute {
    @Id
    private String id;
    private String uri;
    private String routeId;
    private String predicates;
    private String filters;
    private String description;
    private Integer orders;
    private String status;
}
