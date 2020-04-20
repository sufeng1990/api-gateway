package ai.shuzhi.iot.gateway.commons.request.bo;

import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author sf
 * @date 2020/4/20 11:05
 */
@EqualsAndHashCode
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PredicateDefinition {
    private String name;
    private Map<String, String> args = new LinkedHashMap<>();
}
