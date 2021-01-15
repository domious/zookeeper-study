package com.hxd.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

public class CuratorTest {
    /**
     * 测试连接
     */
    @Test
    public void testConnect(){


        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(3000, 10);

        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.23.128:2181", 60 * 1000, 15 * 1000, retry);
        client.start();

    }
}
