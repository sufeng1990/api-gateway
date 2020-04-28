package ai.shuzhi.iot.gateway.commons.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 调用Spring bean对象
 * <p>
 * SpringContextUtils class
 *
 * @author zxb
 * @date 2019/05/24
 */
@Slf4j
@Component
public class SpringContextUtils implements ApplicationContextAware {

    /**
     * spring 上下文
     */
    private static volatile ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        if (Objects.isNull(SpringContextUtils.context)) {
            SpringContextUtils.setContext(applicationContext);
        }
    }

    /**
     * 返回spring 上下文对象
     *
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return context;
    }

    /**
     * 设置静态属性
     *
     * @param context
     */
    protected static void setContext(ApplicationContext context) {
        if (Objects.isNull(SpringContextUtils.context)) {
            SpringContextUtils.context = context;
        }
    }

    /**
     * 根据名称获取Bean实例
     *
     * @param name 名称
     * @return Bean Object
     */
    public static Object getBean(String name) {
        checkApplicationContext();
        return getApplicationContext().getBean(name);
    }

    /**
     * 根据class获取Bean实例
     *
     * @param clazz 类
     * @param <T>   泛型
     * @return Bean <T>
     */
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 根据名称和指定的类型返回Bean实例
     *
     * @param name  名称
     * @param clazz 类
     * @param <T>   泛型
     * @return Bean <T>
     */
    public static <T> T getBean(String name, @Nullable Class<T> clazz) {
        checkApplicationContext();
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 执行方法
     *
     * @param classFullName
     * @param implClassSimpleName
     * @param methodName
     * @param paramTypeClass
     * @param param
     * @param error
     * @return java.lang.Object
     * @author 迁华天
     * @date 2019/11/30
     */
    public static Object invokeMethod(String classFullName, String implClassSimpleName, String methodName, Class<?> paramTypeClass,
                                      Object param, Object error) {
        try {
            Class<?> clazz = Class.forName(classFullName);
            Object obj = getBean(implClassSimpleName, clazz);
            return obj.getClass().getDeclaredMethod(methodName, paramTypeClass).invoke(obj, param);
        } catch (Exception e) {
            log.error("Invoke method error.", e);
            return error;
        }
    }


    /**
     * 清除context静态变量
     */
    public static void cleanApplicationContext() {
        checkApplicationContext();
        context = null;
    }

    /**
     * 检测上下文是否初始化成功
     */
    private static void checkApplicationContext() {
        if (context == null) {
            throw new IllegalStateException("applicationContext未注入," +
                    "请在applicationContext.xml中定义SpringContextHolder");
        }
    }
}
