package com.hxd.curator;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class LockTest {


//    private CuratorFramework client;

    public static void main(String[] args) {
        ticket12306 ticket12306 = new ticket12306();

        Thread t1 = new Thread(ticket12306,"携程");
        Thread t2 = new Thread(ticket12306,"美团");

        t1.start();
        t2.start();

    }









}
