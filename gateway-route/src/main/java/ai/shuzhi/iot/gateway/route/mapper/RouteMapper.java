package ai.shuzhi.iot.gateway.route.mapper;

import ai.shuzhi.iot.gateway.route.entity.GatewayRoute;
import ai.shuzhi.iot.gateway.route.entity.Route;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 苏峰
 */
@Mapper
public interface RouteMapper extends BaseMapper<GatewayRoute> {

}
