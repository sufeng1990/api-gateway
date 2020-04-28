package ai.shuzhi.iot.gateway.aouth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * @author sf
 * @date 2020/4/26 15:27
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "oauth2";


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        // 如果关闭 stateless，则 accessToken 使用时的 session id 会被记录，后续请求不携带 accessToken 也可以正常响应
        // 如果 stateless 为 true 打开状态，则 每次请求都必须携带 accessToken 请求才行，否则将无法访问
        resources.resourceId(RESOURCE_ID).stateless(true);
    }

    /**
     * 为oauth2单独创建角色，这些角色只具有访问受限资源的权限，可解决token失效的问题
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                // 获取登录用户的 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                // 资源服务器拦截的路径 注意此路径不要和主过滤器冲突
                .requestMatchers().antMatchers("/user/oauth/**");
        //
        http
                .authorizeRequests()
                // 配置资源服务器已拦截的路径才有效
                .antMatchers("/user/oauth/**").authenticated();
        // .access(" #oauth2.hasScope('select') or hasAnyRole('ROLE_超级管理员', 'ROLE_设备管理员')");
        //
        http
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}
