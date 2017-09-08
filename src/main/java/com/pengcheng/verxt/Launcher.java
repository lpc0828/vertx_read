package com.pengcheng.verxt;

import com.pengcheng.verxt.instances.MyFirstVerticle;
import io.vertx.core.Vertx;

public class Launcher {

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(MyFirstVerticle.class.getName());
    }
}
