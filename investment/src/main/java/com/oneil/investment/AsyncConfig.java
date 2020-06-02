package com.oneil.investment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name ="taskExecutor")
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("userThread-");
        executor.initialize();
        return executor;
    }
    
    @Bean("customFixedThreadPool")
    public ExecutorService customFixedThreadPool() {
        return Executors.newFixedThreadPool(2, new CustomizableThreadFactory("customFixedThreadPool"));
    }

}