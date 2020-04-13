package ai.shuzhi.iot.platform.commons.config;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

/**
 * ExecutorConfigurer class
 *
 * @author zxb
 * @date 2019/10/21
 */
@Configuration
public class ExecutorConfigurer {

    @Bean
    public AsyncConfigurer asyncConfigurer() {
        AsyncUncaughtExceptionHandler handler = new SimpleAsyncUncaughtExceptionHandler();

        return new AsyncConfigurer() {
            @Override
            @SneakyThrows
            public Executor getAsyncExecutor() {
                return threadPoolTaskExecutor().getObject();
            }

            @Override
            public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
                return handler;
            }
        };
    }

    @Bean
    @ConfigurationProperties(prefix = "platform.executor")
    @Primary
    public FactoryBean<ScheduledExecutorService> threadPoolTaskExecutor() {
        ThreadPoolExecutorFactoryBean executor = new ThreadPoolExecutorFactoryBean() {

            /**
             *
             */
            private static final long serialVersionUID = -491815203266450873L;

            @Override
            protected ScheduledThreadPoolExecutor createExecutor(int corePoolSize, int maxPoolSize,
                int keepAliveSeconds, BlockingQueue<Runnable> queue, ThreadFactory threadFactory,
                RejectedExecutionHandler rejectedExecutionHandler) {

                ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(
                    corePoolSize,
                    threadFactory);
                // 调度线程池执行器继承ThreadPoolExecutor，有一些继承的调校方法，实际上没有什么用处。
                // 在特殊情况下，因为用corePoolSize的数量，创建一个固定的线程池和无界队列，
                // 调整maximumPoolSize，将没有什么用处。另外不将以将核心线程池数量设置0和
                // 用allowCoreThreadTimeOut，因为这也许导致在任务延时时间过期时，线程池没有线程可以用于
                // 执行任务。
                // poolExecutor.setMaximumPoolSize(maxPoolSize)
                poolExecutor.setRejectedExecutionHandler(rejectedExecutionHandler);
                poolExecutor.setKeepAliveTime(keepAliveSeconds, TimeUnit.SECONDS);

                return poolExecutor;
            }
        };
        executor.setThreadNamePrefix("platform-thread-");
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors() * 2);
        return (FactoryBean) executor;
    }
}
