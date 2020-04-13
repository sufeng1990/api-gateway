package ai.shuzhi.iot.gateway.apigateway.biz.impl;

import ai.shuzhi.iot.gateway.apigateway.biz.TestBiz;
import ai.shuzhi.iot.gateway.apigateway.entity.Route;
import ai.shuzhi.iot.gateway.apigateway.mapper.TestMapper;
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
