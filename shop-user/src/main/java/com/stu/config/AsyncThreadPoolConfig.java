package com.stu.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Log4j2
@Configuration
//@EnableAsync
public class AsyncThreadPoolConfig
//        implements AsyncConfigurer
{

//    @Override
//    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//        return (ex, method, params) -> log.info(String.format("执行异步任务'%s'", method), ex);
//
//    }

    @Bean("taskExecutor")
//    @Override
    public ThreadPoolExecutor getAsyncExecutor() {
        ThreadPoolExecutor executor = new MyExecutor(3, 10,
                10, TimeUnit.SECONDS, new ArrayBlockingQueue(100));

//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(3);
//        executor.setMaxPoolSize(10);
//        executor.setQueueCapacity(100);
//        executor.setKeepAliveSeconds(20);
//        executor.setThreadNamePrefix("Async-Service-");

        // 线程池对拒绝任务的处理策略
        // CallerRunsPolicy：由调用线程（提交任务的线程）处理该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
//        executor.initialize();
        return executor;
    }

    @PostConstruct
    public void timer() {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) getAsyncExecutor();
//                ThreadPoolExecutor threadPoolExecutor = executor.getThreadPoolExecutor();

                int poolSize = threadPoolExecutor.getPoolSize();
                int corePoolSize = threadPoolExecutor.getCorePoolSize();
                long taskCount = threadPoolExecutor.getTaskCount();
                int size = threadPoolExecutor.getQueue().size();
                int activeCount = threadPoolExecutor.getActiveCount();
                int maximumPoolSize = threadPoolExecutor.getMaximumPoolSize();
                long completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
                System.out.println("corePoolSize ----" + corePoolSize);
                System.out.println("completedTaskCount ----" + completedTaskCount);
                System.out.println("poolSize ---" + poolSize);
                System.out.println("activeCount ---" + activeCount);
                System.out.println("Queue size --- " + size);
                System.out.println("taskCount ----" + taskCount);
                System.out.println("maximumPoolSize ----" + maximumPoolSize);
                System.out.println("-------------------------------" + new Date());
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 1000, 50000);
    }
}
