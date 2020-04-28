package ai.shuzhi.iot.gateway.commons;

/**
 * 统一状态码
 * <p>
 * CodeConstant class
 *
 * @author zxb
 * @date 2019/05/24
 */
public interface CodeConstant {

	/**
	 * 标识符号，告诉业务端需要验证图片验证码
	 */
	String SHOW_CAPTCHA = "validate_captcha";

	/**
	 * 成功
	 */
	String SUCCESS = "200";
	/**
	 * 未认证，禁止访问
	 */
	String UNAUTHENTIC = "9001";
	/**
	 * 未授权，禁止访问
	 */
	String UNAUTHORIZED = "9002";
	/**
	 * body is empty
	 */
	String BODY_EMTPY = "9900";
	/**
	 * 服务调用客户端异常
	 */
	String FATAL_NETFLIX_CLIENT = "9972";
	/**
	 * 服务调用重试异常
	 */
	String FATAL_NETFLIX_RETRY = "9973";
	/**
	 * 空指针异常
	 */
	String FATAL_NULL_POINTER = "9974";
	/**
	 * 计算异常
	 */
	String FATAL_ARITHMETIC = "9975";
	/**
	 * 类型转换异常
	 */
	String FATAL_CLASS_CAST = "9976";
	/**
	 * 集合负数异常
	 */
	String FATAL_NEGATIVE_ARRAY_SIZE = "9977";
	/**
	 * 集合超出范围异常
	 */
	String FATAL_ARRAY_INDEX_OUT_OF_BOUNDS = "9978";
	/**
	 * 数字格式异常
	 */
	String FATAL_NUMBER_FORMAT = "9979";
	/**
	 * SQL异常
	 */
	String FATAL_SQL = "9980";
	/**
	 * 读写异常
	 */
	String FATAL_IO = "9981";
	/**
	 * 方法未找到异常
	 */
	String FATAL_NO_SUCH_METHOD = "9982";
	/**
	 * 请求方法不支持异常
	 */
	String FATAL_HTTP_REQUEST_METHOD_NOT_SUPPORTED = "9983";
	/**
	 * 请求类型不支持异常
	 */
	String FATAL_HTTP_MEDIA_TYPE_NOT_SUPPORTED = "9984";
	/**
	 * 请求类型不接受异常
	 */
	String FATAL_HTTP_MEDIA_TYPE_NOT_ACCEPTABLE = "9985";
	/**
	 * 缺失路径变量异常
	 */
	String FATAL_MISSING_PATH_VARIABLE = "9986";
	/**
	 * 缺失Servlet请求参数异常
	 */
	String FATAL_MISSING_SERVLET_REQUEST_PARAMETER = "9987";
	/**
	 * Servlet请求绑定异常
	 */
	String FATAL_REQUEST_BINDING = "9988";
	/**
	 * 转换不支持异常
	 */
	String FATAL_CONVERSION_NOT_SUPPORTED = "9989";
	/**
	 * 类型不匹配异常
	 */
	String FATAL_TYPE_MISMATCH = "9990";
	/**
	 * HttpMessage不可读异常
	 */
	String FATAL_HTTP_MESSAGE_NOT_READABLE = "9991";
	/**
	 * HttpMessage不可写异常
	 */
	String FATAL_HTTP_MESSAGE_NOT_WRITABLE = "9992";
	/**
	 * 参数校验无效异常
	 */
	String FATAL_METHOD_ARGUMENT_NOT_VALID = "9993";
	/**
	 * 缺失Servlet请求异常
	 */
	String FATAL_MISSING_SERVLET_REQUEST_PART = "9994";
	/**
	 * 绑定异常
	 */
	String FATAL_BIND = "9995";
	/**
	 * 找不到处理器异常
	 */
	String FATAL_NO_HANDLER_FOUND = "9996";
	/**
	 * 异步请求超时异常
	 */
	String FATAL_ASYNC_REQUEST_TIMEOUT = "9997";
	/**
	 * REST请求异常
	 */
	String FATAL_REST_CLIENT_RESPONSE = "9998";
	/**
	 * 未知错误
	 */
	String FATAL_UNKNOWN = "9999";
}
