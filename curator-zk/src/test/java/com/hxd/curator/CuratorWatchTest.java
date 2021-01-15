package com.hxd.curator;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CuratorWatchTest {


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


    @After
    public void close(){
        System.out.println(5);
        if (client != null){
            client.close();
        }
    }

    @Test
    public void testNodeCache() throws Exception {

        //创建nodecache对象
        NodeCache nodeCache = new NodeCache(client, "/app1/app2");
        //创建监听器
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("change node");
                //获取修改后数据
                byte[] data = nodeCache.getCurrentData().getData();
                System.out.println(new String(data));
            }
        });
        //开启监听，加载缓冲数据
        nodeCache.start(true);

        while (true){}

    }







}
