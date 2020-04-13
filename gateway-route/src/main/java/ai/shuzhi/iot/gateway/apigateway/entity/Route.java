package ai.shuzhi.iot.gateway.apigateway.entity;

import lombok.Data;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.io.Serializable;

/**
 * 应用网关路由表网关(Route)实体类
 *
 * @author makejava
 * @since 2020-04-09 13:55:36
 */
@Data
public class Route extends RouteDefinition implements Serializable {
    private static final long serialVersionUID = 601250677868806284L;
    /**
    * 转发服务id
    */
    private String serviceId;
    /**
    * 路由状态：可用，停用
    */
    private Object status;
    /**
    * 是否删除
    */
    private Object delFlag;
    /**
    * 版本控制
    */
    private String version;
    /**
    * 创建者Id
    */
    private String creatorId;
    /**
    * 创建时间
    */
    private Long createDate;
    /**
    * 修改者ID
    */
    private String updateId;
    /**
    * 修改时间
    */
    private Long updateDate;
    /**
    * 备注
    */
    private String remarks;

}