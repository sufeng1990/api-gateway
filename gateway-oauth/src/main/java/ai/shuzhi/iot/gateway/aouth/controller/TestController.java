package ai.shuzhi.iot.gateway.aouth.controller;

import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author sf
 * @date 2020/4/17 11:25
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class TestController {

    @Autowired
    RestTemplate restTemplate;


    @GetMapping("/test")
    public Boolean test() {
        log.info("oauth test()被調用");
//        log.info(authentication,url,method);
        return Boolean.TRUE;
    }

    /**
     * 获取授权码code，再请求获取token
     *
     * @param code
     * @return
     */
    @GetMapping("/code")
    public ResponseEntity code(@RequestParam("code") String code) {

        log.info("---- 获取授权码：{} ----", code);

        MultiValueMap<String, String> tokenParams = new LinkedMultiValueMap<>();
        tokenParams.add("grant_type", "authorization_code");
        tokenParams.add("code", code);
        tokenParams.add("client_id", "123");
        tokenParams.add("client_secret", "123");
        tokenParams.add("redirect_uri", "http://localhost:5003/user/code");
        tokenParams.add("scope", "all");

        HttpHeaders tokenHeader = new HttpHeaders();
        tokenHeader.set("Content-Type", "multipart/form-data");
        HttpEntity<MultiValueMap<String, String>> requestEntity =
                new HttpEntity<>(tokenParams, tokenHeader);

        ResponseEntity<String> tokenResult = restTemplate.postForEntity(
                "http://localhost:5002/oauth/token", requestEntity, String.class);


        log.info("---- 获取token结果：{} ----", tokenResult);


        String token = new JsonParser().parse(tokenResult.getBody()).
                getAsJsonObject().get("access_token").getAsString();

        log.info("---- access_token：{} ----", token);

        //访问资源服务，仅仅能用来验证登录效果
        HttpHeaders resourceHeader = new HttpHeaders();
        resourceHeader.set("Authorization", "Bearer " + token);

        ResponseEntity<String> resourceResult = restTemplate.exchange(
                "http://localhost:5004/getResource", HttpMethod.GET,
                new HttpEntity<String>(null, resourceHeader), String.class);

        log.info("获取资源的结果：{}", resourceResult);

        return tokenResult;
    }
}
