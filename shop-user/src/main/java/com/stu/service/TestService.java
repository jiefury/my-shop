package com.stu.service;

import com.stu.config.thread.MyThread;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
public class TestService {

    @Autowired
    private ThreadPoolExecutor taskExecutor;

    //    @Async("taskExecutor")
    public void getUser() {
        taskExecutor.submit(new MyThread());

        log.info(Thread.currentThread() + "--------" + "thead start");
//        double n = 1 / 0;
//        try {
//            TimeUnit.SECONDS.sleep(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        log.info(Thread.currentThread() + "--------" + "thead end");
    }
}
