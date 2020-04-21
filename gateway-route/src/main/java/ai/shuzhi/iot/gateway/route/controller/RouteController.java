package ai.shuzhi.iot.gateway.route.controller;


import org.springframework.web.bind.annotation.*;


/**
 * 应用网关路由表网关(Route)表控制层
 *
 * @author makejava
 * @since 2020-04-09 13:55:38
 */
@RestController
@RequestMapping("aa")
public class RouteController {
    /**
     * 服务对象
     */
//    @Resource
//    private RouteService routeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("bb")
    public String selectOne(String id) {
//        return this.routeService.queryById(id);
        return "sadsadasdadasdas";
    }
}