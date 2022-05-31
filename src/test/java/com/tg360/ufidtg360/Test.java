package com.tg360.ufidtg360;

import com.google.common.base.Strings;
import com.tg360.ufidtg360.common.support.TgCookieGenerator;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
//        String tgMid = null;
//
//        System.out.println("tgMid: "+tgMid);
//
//
//        tgMid = Strings.nullToEmpty(tgMid);
//        System.out.println("tgMid: "+tgMid);

        for (int ii = 0; ii < 10000; ii++) {
            Map<String, Object> temp = new HashMap<String, Object>();
            TgCookieGenerator cookieGenerator = new TgCookieGenerator();
            String tgMid = cookieGenerator.getGeneratorTgCookievalue();
            String tgId = cookieGenerator.getGeneratorTgCookievalue();

            Cookie cookieTgMid = new Cookie("_tg_mid", tgMid);
            cookieTgMid.setMaxAge((86400 * 365) * 20);
            cookieTgMid.setPath("/");
            temp.put(tgMid, cookieTgMid);

            Cookie cookieTgId = new Cookie("_tg_id", tgId);
            cookieTgId.setMaxAge((86400 * 365) * 20);
            cookieTgId.setPath("/");
            temp.put(tgId, cookieTgId);
            System.out.println("ii: "+ii);
        }
    }
}
