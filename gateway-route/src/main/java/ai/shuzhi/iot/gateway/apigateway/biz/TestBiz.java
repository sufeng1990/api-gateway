package ai.shuzhi.iot.gateway.apigateway.biz;

import ai.shuzhi.iot.gateway.apigateway.entity.Route;

import java.util.List;

/**
 * @author 苏峰
 *
 */
public interface TestBiz {

    /**
     * 测试方法
     *
     * @return  返回测试
     */
    public List<Route> test();
}
