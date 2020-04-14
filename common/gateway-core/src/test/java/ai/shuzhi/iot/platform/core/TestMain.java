package ai.shuzhi.iot.platform.core;

import ai.shuzhi.iot.platform.commons.response.vo.ComboVO;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.function.Function;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.web.util.HtmlUtils;

/**
 * TestMain class
 *
 * @author zxb
 * @date 2019/12/19
 */
public class TestMain {

    Function<Integer, ComboVO> func = (x) -> {
        if (x % 2 == 0) {
            ComboVO vo = new ComboVO();
            vo.setId(x.toString());
            vo.setName("测试");
            return vo;
        } else {
            return null;
        }
    };

    @Test
    public void testDecode() {
        String a = "&amp;";
        String b = HtmlUtils.htmlUnescape(a);
        System.err.println("b: " + b);
    }

    @Test
    public void testNull() {
        Lists.newArrayList(1, 2, 3, 4).forEach(i -> {
            ComboVO vo = func.apply(i);
            System.err.println("vo: " + vo);
        });
        System.err.println("--------------------");
        Map<String, Integer> map = Maps.newHashMap();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.forEach((k, v) -> {
            ComboVO vo = func.apply(v);
            System.err.println("vo: " + vo);
        });
    }

}
