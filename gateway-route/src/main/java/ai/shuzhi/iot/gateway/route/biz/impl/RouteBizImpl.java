package ai.shuzhi.iot.gateway.route.biz.impl;

import ai.shuzhi.iot.gateway.route.biz.RouteBiz;
import ai.shuzhi.iot.gateway.route.entity.Route;
import ai.shuzhi.iot.gateway.route.mapper.RouteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RouteBizImpl implements RouteBiz {

    @Resource
    private RouteMapper routeMapper;

    @Override
    public List<Route> selectAll() {
        return routeMapper.selectAll();
    }
}
