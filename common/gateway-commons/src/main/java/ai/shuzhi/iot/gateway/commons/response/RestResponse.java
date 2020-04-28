package ai.shuzhi.iot.gateway.commons.response;

import ai.shuzhi.iot.gateway.commons.CodeConstant;
import ai.shuzhi.iot.gateway.commons.format.FormatUtils;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Map;

/**
 * RestResponseclass
 *
 * @author zxb
 * @date 2019/05/24
 */
@ApiModel(value = "响应消息")
public class RestResponse<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4699093329458798931L;

    /**
     * 非异常调用栈索引位置
     */
    private static final int STACK_TRACE_ELEMENT_INX = 2;

    /**
     * 响应消息头
     */
    @ApiModelProperty(value = "响应消息头")
    private RestResponseHeader header;

    /**
     * 响应消息体（泛型）
     */
    @ApiModelProperty(value = "响应消息体（泛型）")
    private T body = null;

    /**
     * 构造函数
     */
    public RestResponse() {
        this.header = new RestResponseHeader();
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    /**
     * 构造函数
     *
     * @param header 响应头
     */
    public RestResponse(RestResponseHeader header) {
        super();
        this.header = header;
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    /**
     * 构造函数
     *
     * @param body 响应消息
     */
    public RestResponse(T body) {
        this.header = new RestResponseHeader();
        this.body = body;
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    /**
     * 构造函数
     *
     * @param header 响应头
     * @param body   响应消息
     */
    public RestResponse(RestResponseHeader header, T body) {
        this.header = header;
        this.body = body;
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    /**
     * 构造函数
     *
     * @param code    状态码
     * @param message 状态描述
     */
    public RestResponse(String code, String message) {
        this.header = new RestResponseHeader(code, message);
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    /**
     * 构造函数
     *
     * @param code     状态码
     * @param message  状态描述
     * @param errTrace 异常堆栈信息
     */
    public RestResponse(String code, String message, String errTrace) {
        this.header = new RestResponseHeader(code, message, errTrace);
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    /**
     * 构造函数
     *
     * @param code     状态码
     * @param message  状态描述
     * @param errType  异常类型
     * @param errTrace 异常堆栈信息
     */
    public RestResponse(String code, String message, String errType, String errTrace) {
        this.header = new RestResponseHeader(code, message, errType, errTrace);
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    /**
     * 构造函数
     *
     * @param code       状态码
     * @param message    状态描述
     * @param subCode    二级状态码
     * @param subMessage 二级状态描述
     * @param errType    异常类型
     * @param errTrace   异常堆栈信息
     */
    public RestResponse(String code, String message, String subCode, String subMessage, String errType,
                        String errTrace) {
        super();
        this.header = new RestResponseHeader(code, message, subCode, subMessage, errType, errTrace);
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    /**
     * 构造函数
     *
     * @param code    状态码
     * @param message 状态描述
     * @param body    响应消息
     */
    public RestResponse(String code, String message, T body) {
        this.header = new RestResponseHeader(code, message);
        this.body = body;
        makeStackTrace(Thread.currentThread().getStackTrace());
    }

    /**
     * 获取状态码
     *
     * @return code
     */
    @JSONField(serialize = false)
    public String fetchCode() {
        return this.getHeader() != null ? this.getHeader().getCode() : null;
    }

    /**
     * 获取状态描述
     */
    @JSONField(serialize = false)
    public String fetchMessage() {
        return this.getHeader() != null ? this.getHeader().getMessage() : null;
    }

    /**
     * 获取二级状态码
     *
     * @return 二级状态码
     */
    @JSONField(serialize = false)
    public String fetchSubCode() {
        return this.getHeader() != null ? this.getHeader().getSubCode() : null;
    }

    /**
     * 获取二级状态描述
     *
     * @return 二级状态描述
     */
    @JSONField(serialize = false)
    public String fetchSubMessage() {
        return this.getHeader() != null ? this.getHeader().getSubMessage() : null;
    }

    /**
     * 静态构造方法，处理成功
     *
     * @param <T> 类型
     * @return RestResponse<T>
     */
    public static <T> RestResponse<T> success() {
        return new RestResponse<>();
    }

    /**
     * 静态构造方法，处理成功
     *
     * @param body 响应消息体
     * @param <T>  类型
     * @return RestResponse<T>
     */
    public static <T> RestResponse<T> success(T body) {
        return new RestResponse<>(body);
    }

    /**
     * Code静态构造方法
     */
    public static <T> RestResponse<T> buildWithCode(String code) {
        RestResponse<T> rr = new RestResponse<>(code, null);
        rr.makeStackTrace(Thread.currentThread().getStackTrace());
        return rr;
    }

    /**
     * 设置状态码
     *
     * @param code 状态码
     * @return RestResponse
     */
    @JSONField(serialize = false)
    public RestResponse<?> withCode(String code) {
        this.header.setCode(code);
        return this;
    }

    /**
     * 设置状态描述
     *
     * @param message 状态描述
     * @return RestResponse
     */
    @JSONField(serialize = false)
    public RestResponse<?> withMessage(String message) {
        this.header.setMessage(message);
        return this;
    }

    /**
     * 设置状态描述
     *
     * @param messagePattern 消息模板
     * @param param          参数
     * @return RestResponse
     */
    @JSONField(serialize = false)
    public RestResponse<?> withMessage(String messagePattern, Map<String, Object> param) {
        String message = FormatUtils.formatFreemarker(messagePattern, param);
        this.header.setMessage(message);
        return this;
    }

    /**
     * 设置二级状态码
     *
     * @param subCode 二级状态码
     */
    @JSONField(serialize = false)
    public RestResponse<?> withSubCode(String subCode) {
        this.header.setSubCode(subCode);
        return this;
    }

    /**
     * 设置二级状态描述
     *
     * @param subMessage 二级状态描述
     */
    @JSONField(serialize = false)
    public RestResponse<?> withSubMessage(String subMessage) {
        this.header.setSubMessage(subMessage);
        return this;
    }

    /**
     * 设置二级状态描述
     *
     * @param subMessagePattern 二级状态描述
     * @param param             替换参数
     */
    @JSONField(serialize = false)
    public RestResponse<?> withSubMessage(String subMessagePattern, Map<String, Object> param) {
        this.header.setSubMessage(FormatUtils.formatFreemarker(subMessagePattern, param));
        return this;
    }

    /**
     * 设置前端是否需要展示图片验证码标识
     *
     * @param flag bool
     * @return RestResponse
     */
    public RestResponse<?> withShowCaptcha(boolean flag) {
        this.header.setShowCaptcha(flag);
        return this;
    }

    /**
     * 设置异常类型
     *
     * @param errType 类型
     * @return RestResponse
     */
    @JSONField(serialize = false)
    public RestResponse<?> withErrType(String errType) {
        this.header.setType(errType);
        return this;
    }

    /**
     * 设置异常堆栈信息
     *
     * @param errTrace 异常堆栈信息
     * @return RestResponse
     */
    @JSONField(serialize = false)
    public RestResponse<?> withErrTrace(String errTrace) {
        this.header.setErrTrace(errTrace);
        return this;
    }

    /**
     * 设置响应消息体(泛型)
     *
     * @param body 响应消息体(泛型)
     * @return RestResponse
     */
    @JSONField(serialize = false)
    public RestResponse<?> withBody(T body) {
        this.body = body;
        return this;
    }

    /**
     * 异常堆栈信息
     */
    @JSONField(serialize = false)
    public RestResponse<?> withStackTraceElements(StackTraceElement[] stackTraceElements) {
        this.header.setStackTraceElements(stackTraceElements);
        return this;
    }

    /**
     * 判断response code 状态是成功的
     *
     * @return boolean
     */
    @JSONField(serialize = false)
    public boolean isSuccess() {
        return this.header == null ? false : CodeConstant.SUCCESS.equals(header.getCode());
    }

    /**
     * 判断response code 状态是失败的
     *
     * @return boolean
     */
    @JSONField(serialize = false)
    public boolean isFailure() {
        return !isSuccess();
    }

    /**
     * 转换RestResponse， 因为返回对象的泛型不一样， 有时个需要转换
     *
     * @return RestResponse
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @JSONField(serialize = false)
    public RestResponse<?> transfer() {
        RestResponse restResponse = new RestResponse();
        restResponse.setHeader(getHeader());
        restResponse.withBody(getBody());
        return restResponse;
    }

    /**
     * 获取非异常调用栈元素
     *
     * @param stackTraceElements 栈信息
     */
    private void makeStackTrace(StackTraceElement[] stackTraceElements) {
        if (stackTraceElements != null && stackTraceElements.length > 1) {
            this.header.setStackTraceElements(new StackTraceElement[]{stackTraceElements[STACK_TRACE_ELEMENT_INX]});
        }
    }

    public RestResponseHeader getHeader() {
        return header;
    }

    public void setHeader(RestResponseHeader header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
