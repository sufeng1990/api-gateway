package ai.shuzhi.iot.gateway.commons.format;

import com.google.common.collect.Maps;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * 字符串格式化
 * <p>
 * FormatUtils class
 *
 * @author zxb
 * @date 2019/05/24
 */
public class FormatUtils {

	public static final Configuration CONFIGURATION = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

	private static Pattern PATTERN = Pattern.compile("\\$\\{\\w+\\}");

	/**
	 * unicode转中文
	 *
	 * @param str 中文编码
	 * @return 转换后的字符串
	 */
	public static String unicodeToString(String str) {
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		Pattern pattern = compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
			ch = (char) Integer.parseInt(matcher.group(2), 16);
			str = str.replace(matcher.group(1), ch + "");
		}
		return str;
	}

	/**
	 * 使用 Java自身的String.format()函数格式化
	 *
	 * @param template 模板 %s, %s
	 * @param params 参数
	 * @return 格式化后字符串
	 */
	public static String format(String template, Object... params) {
		if (StringUtils.isBlank(template) || params == null) {
			return null;
		}
		return String.format(template, params);
	}

	/**
	 * MessageFormat渲染模板 {0}, {1}
	 *
	 * @param template 模板
	 * @param params 参数
	 * @return 格式化后字符串
	 */
	public static String formatMessage(String template, Object... params) {
		if (StringUtils.isBlank(template) || params == null) {
			return null;
		}
		return MessageFormat.format(template, params);
	}

	/**
	 * 使用freemarker渲染模板 ${name}
	 *
	 * @param template 模板
	 * @param params 参数
	 * @return 格式化后字符串
	 */
	public static String formatFreemarker(String template, Map<String, Object> params) {
		if (StringUtils.isBlank(template) || params == null) {
			return null;
		}
		StringWriter writer = new StringWriter();
		try {
			Template tpl = new Template("strTpl", template, CONFIGURATION);
			tpl.process(params, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer.toString();
	}

	/**
	 * 自定义格式化
	 *
	 * @param template 模板
	 * @param params 参数
	 * @return 格式化后字符串
	 */
	public static String formatTemplate(String template, Map<String, Object> params) {
		if (StringUtils.isBlank(template) || params == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		Matcher matcher = PATTERN.matcher(template);
		while (matcher.find()) {
			String param = matcher.group();
			Object value = params.get(param.substring(2, param.length() - 1));
			matcher.appendReplacement(sb, value == null ? "" : value.toString());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	public static void main(String[] args) {
		String fmt = "%s %s";
		System.err.println("格式化信息：" + format(fmt, "hello", "world"));
		fmt = "{1} '{}' {0}";
		System.err.println("格式化信息：" + formatMessage(fmt, "hello", "world"));
		Map<String, Object> params = Maps.newHashMap();
		params.put("name", "张三");
		params.put("age", 11);
		fmt = "姓名：${name} 年龄：${age} ";
		System.err.println("格式化信息：" + formatFreemarker(fmt, params));
		System.err.println("格式化信息：" + formatTemplate(fmt, params));
	}
}
