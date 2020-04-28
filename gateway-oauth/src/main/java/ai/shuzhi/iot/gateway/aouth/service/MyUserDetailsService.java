package ai.shuzhi.iot.gateway.aouth.service;

import ai.shuzhi.iot.gateway.aouth.entity.GatewayUser;
import ai.shuzhi.iot.gateway.aouth.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author sf
 * @date 2020/4/26 17:48m
 */
@Component("myUserDetailsService")
public class MyUserDetailsService  implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String passwd = "";
        System.out.println("收到的账号"+username);
        GatewayUser gatewayUser = GatewayUser.builder().username(username).build();
        GatewayUser user = userMapper.selectOne(gatewayUser);
        if (Objects.nonNull(user)){
            passwd = user.getPassword();
        }else {
            throw new RuntimeException("登录账号不存在");
        }
        System.out.println("查到的密码"+passwd);
        return new User(username, passwd, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
    }
}
