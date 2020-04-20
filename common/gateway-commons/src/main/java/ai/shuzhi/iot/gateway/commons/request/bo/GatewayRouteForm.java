package ai.shuzhi.iot.gateway.commons.request.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sf
 * @date 2020/4/20 11:01
 */
@Data
public class GatewayRouteForm {
    @NotEmpty(message = "网关断言不能为空")
    @ApiModelProperty(value = "网关断言")
    private List<PredicateDefinition> predicates = new ArrayList<>();

    @ApiModelProperty(value = "网关过滤器信息")
    private List<FilterDefinition> filters = new ArrayList<>();

    @NotBlank(message = "uri不能为空")
    @ApiModelProperty(value = "网关uri")
    private String uri;

    @NotBlank(message = "路由id不能为空")
    @ApiModelProperty(value = "网关路由id")
    private String routeId;

    @ApiModelProperty(value = "排序")
    private Integer orders = 0;

    @ApiModelProperty(value = "网关路由描述信息")
    private String description;
}
