package ai.shuzhi.iot.gateway.route.mapper;


import ai.shuzhi.iot.gateway.route.entity.Route;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<Route> selectAll();
}
