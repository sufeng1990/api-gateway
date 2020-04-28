package ai.shuzhi.iot.gateway.commons.response;

import ai.shuzhi.iot.gateway.commons.CodeConstant;
import ai.shuzhi.iot.gateway.commons.spring.SpringContextUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.core.env.Environment;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

/**
 * RestResponseHeader class
 *
 * @author zxb
 * @date 2019/05/24
 */
@ApiModel(value = "响应消息头")
public class RestResponseHeader implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9202258750311469105L;
	/**
	 * 获取应用名称的KEY
	 */
	private static final String KEY_APP = "spring.application.name";
	/**
	 * app
	 */
	@ApiModelProperty(value = "app")
	private String app = null;
	/**
	 * 状态码
	 */
	@ApiModelProperty(value = "状态码")
	private String code = CodeConstant.SUCCESS;
	/**
	 * 状态描述
	 */
	@ApiModelProperty(value = "状态描述")
	private String message;
	/**
	 * 异常类型
	 */
	@ApiModelProperty(value = "异常类型")
	private String type;
	/**
	 * 二级状态码
	 */
	@ApiModelProperty(value = "二级状态码")
	private String subCode;
	/**
	 * 二级状态描述
	 */
	@ApiModelProperty(value = "二级状态描述")
	private String subMessage;
	/**
	 * 扩展字段
	 */
	@ApiModelProperty(value = "扩展字段")
	private String ext;
	/**
	 * 扩展字段
	 */
	@ApiModelProperty(value = "扩展字段")
	private Map<String, Object> extMap;
	/**
	 * 异常堆栈信息
	 */
	@ApiModelProperty(value = "异常堆栈信息")
	private String errTrace;
	/**
	 * 调用跟踪栈元素
	 */
	@ApiModelProperty(value = "调用跟踪栈元素")
	private StackTraceElement[] stackTraceElements;
	/**
	 * 是否显示验证码
	 */
	@ApiModelProperty(value = "是否显示验证码")
	private Boolean showCaptcha = false;

	/**
	 * 构造函数
	 */
	public RestResponseHeader() {
		this.app = Optional.ofNullable(app)
				.orElse(SpringContextUtils.getBean(Environment.class).getProperty(KEY_APP));
	}

	/**
	 * 构造函数
	 *
	 * @param code 状态码
	 * @param message 状态描述
	 */
	public RestResponseHeader(String code, String message) {
		app = Optional.ofNullable(app)
				.orElse(SpringContextUtils.getBean(Environment.class).getProperty(KEY_APP));
		this.code = code;
		this.message = message;
	}

	/**
	 * 构造函数
	 *
	 * @param code 状态码
	 * @param message 状态描述
	 * @param errTrace 异常堆栈信息
	 */
	public RestResponseHeader(String code, String message, String errTrace) {
		super();
		this.code = code;
		this.message = message;
		this.errTrace = errTrace;
		app = Optional.ofNullable(app)
				.orElse(SpringContextUtils.getBean(Environment.class).getProperty(KEY_APP));
	}

	/**
	 * 构造函数
	 *
	 * @param code 状态码
	 * @param message 状态描述
	 * @param errType 异常类型
	 * @param errTrace 异常堆栈信息
	 */
	public RestResponseHeader(String code, String message, String errType, String errTrace) {
		super();
		this.code = code;
		this.message = message;
		this.type = errType;
		this.errTrace = errTrace;
		app = Optional.ofNullable(app)
				.orElse(SpringContextUtils.getBean(Environment.class).getProperty(KEY_APP));
	}

	/**
	 * 构造函数
	 *
	 * @param code 状态码
	 * @param message 状态描述
	 * @param subCode 二级状态码
	 * @param subMessage 二级状态描述
	 * @param errType 异常类型
	 * @param errTrace 异常堆栈信息
	 */
	public RestResponseHeader(String code, String message, String subCode, String subMessage,
			String errType, String errTrace) {
		super();
		this.code = code;
		this.message = message;
		this.subCode = subCode;
		this.subMessage = subMessage;
		this.type = errType;
		this.errTrace = errTrace;
		app = Optional.ofNullable(app)
				.orElse(SpringContextUtils.getBean(Environment.class).getProperty(KEY_APP));
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public Map<String, Object> getExtMap() {
		return extMap;
	}

	public void setExtMap(Map<String, Object> extMap) {
		this.extMap = extMap;
	}

	public String getErrTrace() {
		return errTrace;
	}

	public void setErrTrace(String errTrace) {
		this.errTrace = errTrace;
	}

	public StackTraceElement[] getStackTraceElements() {
		if (stackTraceElements == null) {
			return null;
		}
		return stackTraceElements.clone();
	}

	public void setStackTraceElements(StackTraceElement[] stackTraceElements) {
		if (stackTraceElements != null) {
			this.stackTraceElements = stackTraceElements.clone();
		} else {
			this.stackTraceElements = null;
		}
	}

	public Boolean getShowCaptcha() {
		return showCaptcha;
	}

	public void setShowCaptcha(Boolean showCaptcha) {
		this.showCaptcha = showCaptcha;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getSubMessage() {
		return subMessage;
	}

	public void setSubMessage(String subMessage) {
		this.subMessage = subMessage;
	}

	@Override
	public String toString() {
		return "RestResponseHeader{" +
				"app='" + app + '\'' +
				", code='" + code + '\'' +
				", message='" + message + '\'' +
				", type='" + type + '\'' +
				", subCode='" + subCode + '\'' +
				", subMessage='" + subMessage + '\'' +
				", ext='" + ext + '\'' +
				", extMap=" + extMap +
				", errTrace='" + errTrace + '\'' +
				", stackTraceElements=" + Arrays.toString(stackTraceElements) +
				", showCaptcha=" + showCaptcha +
				'}';
	}
}
