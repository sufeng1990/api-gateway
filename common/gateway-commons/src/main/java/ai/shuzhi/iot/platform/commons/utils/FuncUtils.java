package ai.shuzhi.iot.platform.commons.utils;

import ai.shuzhi.iot.platform.commons.constant.IotCodeConstant;
import com.google.common.base.Joiner;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

/**
 * FuncUtils class
 *
 * @author zxb
 * @date 2019/11/07
 */
public class FuncUtils {

    /**
     * 是否存在有效值（大于0）
     *
     * @param x 参数
     * @return boolean
     */
    public static final boolean has(Long x) {
        return x != null && x > 0;
    }

    /**
     * 是否存在有效值（大于0）
     *
     * @param x 参数
     * @return boolean
     */
    public static final boolean has(Integer x) {
        return x != null && x > 0;
    }

    /**
     * 是否存在有效值（大于0）
     *
     * @param x 参数
     * @return boolean
     */
    public static final boolean has(Short x) {
        return x != null && x > 0;
    }

    /**
     * 返回Key，使用","分割符将字符串拼接在一起
     *
     * @param key 待拼接参数 如: ["a", "b", "c"]
     * @return 字符串 a:b:c
     */
    public static final String getKey(String... key) {
        if (Objects.isNull(key)) {
            return "";
        }
        return Joiner.on(IotCodeConstant.COLON).join(key);
    }

    /**
     * 生成SQL
     *
     * @param iterable 参数
     * @param property 列名称
     * @return SQL
     */
    public static String getCondition(Iterable iterable, String property) {
        return getCondition(iterable, false, property);
    }

    /**
     * 生成SQL
     *
     * @param iterable 参数
     * @param hasWhere 是否需要添加where
     * @param property 列名称
     * @return SQL
     */
    public static String getCondition(Iterable iterable, boolean hasWhere, String property) {
        StringBuilder sb = new StringBuilder();
        Optional.ofNullable(iterable).ifPresent(iterator -> {
            if (hasWhere) {
                sb.append(" where ");
            }
            Iterator it = iterator.iterator();
            sb.append("(");
            while (it.hasNext()) {
                sb.append(" " + property + " = '");
                sb.append(it.next());
                sb.append("'");
                if (it.hasNext()) {
                    sb.append(" or ");
                }
            }
            sb.append(")");
        });
        return sb.toString();
    }
}
