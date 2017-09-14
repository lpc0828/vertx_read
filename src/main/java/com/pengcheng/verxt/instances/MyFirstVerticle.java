package com.pengcheng.verxt.instances;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;

import java.util.concurrent.atomic.AtomicInteger;

public class MyFirstVerticle extends AbstractVerticle {

    int i =0;
    AtomicInteger index = new AtomicInteger(0);

    @Override
    public void start() {
        vertx.createHttpServer().requestHandler(req -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String threadName = genThreadName();
            System.out.println(threadName+" 开始执行。。。。。。");
            long start = System.currentTimeMillis();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello World");
            i++;
            System.out.println(threadName+" 执行完毕。。。。。。i="+i + ", cost:"+(System.currentTimeMillis()-start));
        }).listen(8083);



        vertx.createHttpServer().requestHandler(req -> {
            String threadName = genThreadName();
            System.out.println(threadName+" 开始执行。。。。。。");
            vertx.executeBlocking(future->{
                String threadName1 = genThreadName();
                System.out.println(threadName1+" async 开始执行。。。。。。");
                int value = index.incrementAndGet();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                future.complete(value);
            }, false, res->{
                String threadName1 = genThreadName();
                System.out.println(threadName1+" res 开始执行。。。。。。");
                if (res.succeeded()) {
                    System.out.println(res.result() + " ["+System.currentTimeMillis()+"] complete......");
                }
                req.response().end();
            });
        }).listen(8084);

    }


    private String genThreadName() {
        String threadName = Thread.currentThread().getName();
        return "["+ System.currentTimeMillis()/1000L + "]"+"[" + threadName + "]";
    }
}
