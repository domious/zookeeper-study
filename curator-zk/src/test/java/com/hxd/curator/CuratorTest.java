package com.hxd.curator;



import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;

import org.apache.curator.retry.ExponentialBackoffRetry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class CuratorTest {


    private CuratorFramework client;





    /**
     * 测试连接
     */

    @Before
    public void testConnect(){


        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(3000, 10);
//        RetryPolicy retry =retry1;

        client = CuratorFrameworkFactory.newClient("192.168.23.128:2181", 60 * 1000, 15 * 1000, retry);
//        client = CuratorFrameworkFactory.builder()
//                .connectString("192.168.23.128:2181")
//                .sessionTimeoutMs(60*1000)
//                .connectionTimeoutMs(15*1000)
//                .retryPolicy(retry)
////                .namespace("app1")
//                .build();

        client.start();
        System.out.println(1);
    }

    /**
     * 创建节点 持久 临时 顺序 数据
     */
    @Test
    public void testCreate() throws Exception {



        String s = client.create().forPath("/app1/app4 ", "init".getBytes());

        System.out.println(s);

    }

    @Test
    public void testGet() throws Exception {
        byte[] data = client.getData().forPath("/app1/app2");
        System.out.println(new String(data));

    }

    @After
    public void close(){
        System.out.println(5);
        if (client != null){
            client.close();
        }
    }

}
