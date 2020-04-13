package ai.shuzhi.iot.gateway.apigateway.mapper;


import ai.shuzhi.iot.gateway.apigateway.entity.Route;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<Route> selectAll();
}
