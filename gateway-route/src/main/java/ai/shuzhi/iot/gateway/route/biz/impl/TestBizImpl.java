package ai.shuzhi.iot.gateway.route.biz.impl;

import ai.shuzhi.iot.gateway.route.biz.TestBiz;
import ai.shuzhi.iot.gateway.route.entity.Route;
import ai.shuzhi.iot.gateway.route.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 苏峰
 */
@Service
public class TestBizImpl implements TestBiz {

    @Resource
    private TestMapper testMapper;


    @Override
    public List<Route> test() {
        return testMapper.selectAll();
    }
}
