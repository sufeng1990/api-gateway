package ai.shuzhi.iot.gateway.aouth.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;

/**
 * @author sf
 * @date 2020/4/26 15:48
 */
@Data
@Builder
public class GatewayUser {
    @Id
    private String id;
    private String name;
    private String username;
    private String password;
    private String tel;
    private String gender;
    private Long createDate;
}
