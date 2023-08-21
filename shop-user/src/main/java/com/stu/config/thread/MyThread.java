package com.stu.config.thread;

public class MyThread implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread() + "--------" + "thead start");
//        double n = 1 / 0;
        System.out.println(Thread.currentThread() + "--------" + "thead end");
//        throw new RuntimeException("RuntimeException " + Thread.currentThread());
    }

    public String getName() {
        return Thread.currentThread() + "";
    }
}