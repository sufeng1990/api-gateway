package ai.shuzhi.iot.gateway.apigateway.mapper;

import ai.shuzhi.iot.gateway.apigateway.entity.Route;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 苏峰
 */
@Mapper
public interface RouteMapper {

    /**
     *  查询所有路由信息
     *
     * @return 返回路由信息
     */
    List<Route> selectAll();
}
