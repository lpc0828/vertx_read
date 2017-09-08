package com.pengcheng.verxt.instances;

import io.vertx.core.AbstractVerticle;

public class MyFirstVerticle extends AbstractVerticle {

    int i =0;

    @Override
    public void start() {
        vertx.createHttpServer().requestHandler(req -> {
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
    }


    private String genThreadName() {
        String threadName = Thread.currentThread().getName();
        return "[" + threadName + "]";
    }
}
