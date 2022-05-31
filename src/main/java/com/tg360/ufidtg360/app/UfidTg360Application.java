package com.tg360.ufidtg360.app;

import com.tg360.ufidtg360.app.vertx.TG360UnifiedVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.tg360.ufidtg360")
public class UfidTg360Application implements CommandLineRunner {

    private Vertx vertx;
    public static void main(String[] args) {
        SpringApplication.run(UfidTg360Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(150));

        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
        vertx.deployVerticle(new TG360UnifiedVerticle());
    }

}