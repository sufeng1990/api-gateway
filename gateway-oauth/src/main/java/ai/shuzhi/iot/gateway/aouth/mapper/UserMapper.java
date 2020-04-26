package ai.shuzhi.iot.gateway.aouth.mapper;

import ai.shuzhi.iot.gateway.aouth.entity.GatewayUser;
import ai.shuzhi.iot.gateway.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author sf
 * @date 2020/4/26 15:37
 */
@Mapper
public interface UserMapper extends BaseMapper<GatewayUser> {

}
