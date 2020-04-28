package ai.shuzhi.iot.gateway.aouth.hadler;

import ai.shuzhi.iot.gateway.commons.response.RestResponse;
import ai.shuzhi.iot.gateway.commons.utils.IPUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sf
 * @date 2020/4/27 10:17
 */
@Component("userLoginSuccessHandler")
@Slf4j
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        //获得授权后可得到用户信息   可使用SecurityUserService进行数据库操作
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        //输出登录提示信息
        log.info("用户 " + userDetails.getUsername() + " 登录");
        log.info("IP :" + IPUtils.getIpAddr(request));

        // 记录登录成功的日志
        //this.saveLog(request, authentication);
        renderSuccessJson(request, response, RestResponse.success(userDetails));
    }

    /**
     * 往 response 写出 json
     *
     * @param response 响应
     * @param result   返回数据
     */
    public static void renderSuccessJson(HttpServletRequest request, HttpServletResponse response, RestResponse result) {
        try {
            String origin = request.getHeader("Origin");
            addResponseHeader(response, origin);
            response.setStatus(200);
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            log.error("Response写出JSON异常，", ex);
        }
    }

    public static void addResponseHeader(HttpServletResponse response, String originHeader) {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        // response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Origin", originHeader);
    }
}
