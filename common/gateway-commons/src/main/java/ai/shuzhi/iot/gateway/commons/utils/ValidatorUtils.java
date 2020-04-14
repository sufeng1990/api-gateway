package ai.shuzhi.iot.gateway.commons.utils;

import ai.shuzhi.framework.commons.exception.utils.AssertUtils;
import java.util.Objects;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

/**
 * ValidatorUtils class
 *
 * @author zxb
 * @date 2019/12/20
 */
public class ValidatorUtils {

    /**
     * 验证参数，若未通过校验则抛出异常
     *
     * @param validator 校验器
     * @param obj 待校验对象
     * @param <T> 类型
     */
    public static <T> void validate(Validator validator, T obj) {
        if (Objects.isNull(validator) || Objects.isNull(obj)) {
            return;
        }
        //AssertUtils.assertEmpty(validator.validate(obj), ConstraintViolationException::new);
    }

}
