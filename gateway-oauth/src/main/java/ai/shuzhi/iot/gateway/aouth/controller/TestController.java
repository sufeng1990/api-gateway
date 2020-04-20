package ai.shuzhi.iot.gateway.aouth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sf
 * @date 2020/4/17 11:25
 */
@RestController
@RequestMapping("/oauth")
@Slf4j
public class TestController {

    @GetMapping("/test")
    public Boolean test() {
        log.info("oauth test()被調用");
//        log.info(authentication,url,method);
        return Boolean.TRUE;
    }
}
