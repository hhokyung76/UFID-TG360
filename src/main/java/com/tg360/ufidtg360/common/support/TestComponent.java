package com.tg360.ufidtg360.common.support;

import io.vertx.core.json.JsonObject;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;

@Component
public class TestComponent {
    LinkedHashMap<String, JsonObject> products = new LinkedHashMap<>();

    public LinkedHashMap<String, JsonObject> getProducts() {
        return products;
    }

    @PostConstruct
    public void start() throws Exception {

        for (int ii = 0; ii < 10; ii++) {
            JsonObject jsonObj = new JsonObject();
            jsonObj.put("name", "tg360" + ii);
            jsonObj.put("addr", "tg360-ADDR-" + ii);
            jsonObj.put("account", "3000" + ii);
            products.put("tg" + ii, jsonObj);
        }
    }
}
