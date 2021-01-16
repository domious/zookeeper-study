package com.hxd.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

public class ticket12306 implements Runnable {

    private int tickets =10;
    private InterProcessMutex lock;
    private CuratorFramework client;

    public ticket12306() {
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(3000, 10);
        client = CuratorFrameworkFactory.newClient("192.168.23.128:2181", 60 * 1000, 15 * 1000, retry);
        client.start();
        lock = new InterProcessMutex(client,"/lock");
    }

    @Override
    public void run() {
        while (true){
            //加锁
            try {
                lock.acquire(3, TimeUnit.SECONDS);
                if (tickets > 0){
                    System.out.println(Thread.currentThread()+":"+tickets);
                    tickets--;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                //释放锁
                try {
                    lock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }






        }
    }


}
