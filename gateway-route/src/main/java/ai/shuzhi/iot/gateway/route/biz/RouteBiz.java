package ai.shuzhi.iot.gateway.route.biz;

import ai.shuzhi.iot.gateway.route.entity.GatewayRoute;
import ai.shuzhi.iot.gateway.route.entity.Route;

import java.util.List;

/**
 *
 * @author 苏峰
 */
public interface RouteBiz extends BaseBiz<GatewayRoute, String> {

    /**
     * 测试方法
     *
     * @return  返回测试
     */
    List<GatewayRoute> selectAll();
}
