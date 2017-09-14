package com.pengcheng.verxt;

import com.pengcheng.verxt.instances.MyFirstVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;

import java.lang.management.*;
import java.util.Map;
import java.util.TreeMap;

public class Launcher {

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(MyFirstVerticle.class.getName());
        /*Vertx.vertx().deployVerticle(MyFirstVerticle.class.getName());*/
//        System.out.println("=========================== 1111111111 ==================================");
//        printThreadInfo();
//        WebClientOptions options = new WebClientOptions();
//        Vertx vertx = Vertx.vertx(new VertxOptions());
//        WebClient client = WebClient.create(vertx, options.setMaxPoolSize(Runtime.getRuntime().availableProcessors()*64));
//        System.out.println("=========================== 2222222222 ==================================");
//        printThreadInfo();
//        for (int i=0; i<10; i++) {
//            client.post(8081, "127.0.0.1", "/xc_sale/uc/auth.do")
//                    .putHeader("platform", "android")
//                    .putHeader("utoken", "10364121-17237-8a17328e-9f7b-4042-a5b5-66efd5508420")
//                    .addQueryParam("trackId", String.valueOf(i))
//                    .send(res -> {
//                        if (res.succeeded()) {
//                            System.out.println(res.result().bodyAsString());
//                        } else if (res.failed()) {
//                            System.out.println("httpRequest Error!");
//                        }
//                    });
//        }
//        System.out.println("send complete!");
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("=========================== 2222222222 ==================================");
//        printThreadInfo();
//        client.close();

    }


    public static void printThreadInfo() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] threadIds = threadMXBean.getAllThreadIds();
        Map<String, ThreadInfo> liveThreadMap = new TreeMap<String, ThreadInfo>();
        for (long threadId : threadIds) {
            // 线程的信息
            ThreadInfo threadInfo = threadMXBean.getThreadInfo(threadId);
            // 线程被阻塞的数量
            threadInfo.getBlockedCount();
            // 被锁定线程的监控信息
            MonitorInfo[] monitorInfos = threadInfo.getLockedMonitors();
            for (MonitorInfo monitorInfo : monitorInfos) {
                int depth = monitorInfo.getLockedStackDepth();
                System.out.println("锁定程度：" + depth);
            }
            // 异步锁定的信息
            LockInfo[] lockinfos = threadInfo.getLockedSynchronizers();
            // 锁定的信息
            for (LockInfo lockInfo : lockinfos) {
                System.out.println("锁定类名称：" + lockInfo.getClassName());
            }

            liveThreadMap.put(threadInfo.getThreadName(), threadInfo);

        }
        for(Map.Entry<String, ThreadInfo> entry : liveThreadMap.entrySet()){
            String threadName = entry.getKey();
            ThreadInfo threadInfo = entry.getValue();
            System.out.println("线程名称：" + threadName + ",状态[" + threadInfo.getThreadState().name() + "]");
        }
    }
}
