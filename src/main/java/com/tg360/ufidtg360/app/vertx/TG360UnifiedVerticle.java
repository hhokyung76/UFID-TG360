package com.tg360.ufidtg360.app.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;

@Slf4j
public class TG360UnifiedVerticle extends AbstractVerticle {
    private LinkedHashMap<String, JsonObject> products = new LinkedHashMap<>();

    public void start() throws Exception {

        for (int ii=0; ii < 10; ii++) {
            JsonObject jsonObj = new JsonObject();
            jsonObj.put("name", "tg360" + ii);
            jsonObj.put("addr", "tg360-ADDR-" + ii);
            jsonObj.put("account", "3000" + ii);
            products.put("tg"+ii, jsonObj);
        }
        log.info("Verticle1 Started....");
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.get("/products/:productID").handler(this::handleGetProduct);
        router.put("/products/:productID").handler(this::handleAddProduct);
        router.get("/products").handler(this::handleListProducts);


        vertx.createHttpServer().requestHandler(router).listen(80);
    }

    private void handleGetProduct(RoutingContext routingContext) {

        log.info("handleGetProduct Router called....");

        String productID = routingContext.request().getParam("productID");
        HttpServerResponse response = routingContext.response();
        if (productID == null) {
            sendError(400, response);
        } else {
            JsonObject product = products.get(productID);
            if (product == null) {
                sendError(404, response);
            } else {
                response.putHeader("content-type", "application/json").end(product.encodePrettily());
            }
        }
    }

    private void handleAddProduct(RoutingContext routingContext) {
        log.info("handleAddProduct Router called....");
        String productID = routingContext.request().getParam("productID");
        HttpServerResponse response = routingContext.response();
        if (productID == null) {
            sendError(400, response);
        } else {
            JsonObject product = (JsonObject) routingContext.body();
            if (product == null) {
                sendError(400, response);
            } else {
                products.put(productID, product);
                response.end();
            }
        }
    }

    private void handleListProducts(RoutingContext routingContext) {
        log.info("handleListProducts Router called....");
        JsonArray arr = new JsonArray();
        products.forEach((k, v) -> arr.add(v));
        routingContext.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
    }
    private void sendError(int statusCode, HttpServerResponse response) {
        response.setStatusCode(statusCode).end();
    }
}
