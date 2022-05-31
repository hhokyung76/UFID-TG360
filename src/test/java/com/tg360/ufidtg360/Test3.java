package com.tg360.ufidtg360;

import com.tg360.ufidtg360.common.support.TgCookieGenerator;
import io.vertx.core.json.JsonObject;

public class Test3 {
    public static void main(String[] args) {
        JsonObject returnJson = new JsonObject();
        returnJson.put("_tg_mid", "tgMid");
        returnJson.put("_tg_id", "tgId");

        System.out.println("returnJson: "+returnJson.toString());
    }
}
