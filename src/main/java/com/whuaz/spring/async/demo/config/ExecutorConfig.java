package com.whuaz.spring.async.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author grez
 * @since 18-11-27
 **/
@Configuration
@EnableAsync
public class ExecutorConfig {

    /**
     * 初始线程数
     */
    private int corePoolSize = 10;

    /**
     * 最大线程数
     */
    private int maxPoolSize = 200;

    /**
     * 阻塞队列大小
     */
    private int queueCapacity = 10;

    private static final String METHOD_ASYNC = "MethodExecutor-";

    /**
     * 简单配置线程池
     * @return
     */
    @Bean("methodAsync")
    public Executor methodAsync() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(METHOD_ASYNC);
        executor.initialize();
        return executor;
    }
}
