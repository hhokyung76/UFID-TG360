package com.tg360.ufidtg360.app.cookie;

import com.google.common.base.Strings;
import com.tg360.ufidtg360.common.support.TgCookieGenerator;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CookieManager {

    public Map<String, String> buildTgCookieInfo(HttpServletRequest request) {
        String tgMode = Strings.nullToEmpty(request.getParameter("_tg_mode"));
        String tgMid = Strings.nullToEmpty(request.getParameter("_tg_mid"));
        String tgId = Strings.nullToEmpty(request.getParameter("_tg_id"));
        String redirectUrl = Strings.nullToEmpty(request.getParameter("redirect_url"));
        String referrer_url = Strings.nullToEmpty(request.getParameter("referrer_url"));

        log.info("_tg_mode=["+tgMode+"]_tg_mid=["+tgMid+"] _tg_id=["+tgId+"]");
        System.out.println("==redirectUrl: "+referrer_url);
        System.out.println("==referrer_url: "+referrer_url);

        if (redirectUrl.indexOf(referrer_url) == -1) {
            redirectUrl = referrer_url + redirectUrl;
        }
        if(redirectUrl.indexOf("?") == -1) {
            redirectUrl += "?";
        }


        TgCookieGenerator cookieGenerator = new TgCookieGenerator();

        if (tgMode.equals("M-1")) {
            tgMid = cookieGenerator.getGeneratorTgCookievalue();
        }
        tgId = cookieGenerator.getGeneratorTgCookievalue();

        redirectUrl += "_tg_mode="+tgMode+"&_tg_mid="+tgMid+"&_tg_id="+tgId+"&_tg_st=Y";

        Map<String, String> returnVal = new HashMap<String, String>();
        returnVal.put("_tg_mid", tgMid);
        returnVal.put("_tg_id", tgId);
        returnVal.put("redirect_url", redirectUrl);

        return returnVal;
    }


    public Map<String, String> buildTgCookieInfoReal(Map<String, String> request) {
        String tgMode = Strings.nullToEmpty(request.get("_tg_mode"));
        String tgMid = Strings.nullToEmpty(request.get("_tg_mid"));
        String tgId = Strings.nullToEmpty(request.get("_tg_id"));
        String redirectUrl = Strings.nullToEmpty(request.get("redirect_url"));
        String referrer_url = Strings.nullToEmpty(request.get("referrer_url"));

        log.info("_tg_mode=["+tgMode+"]_tg_mid=["+tgMid+"] _tg_id=["+tgId+"]");
        System.out.println("==redirectUrl: "+referrer_url);
        System.out.println("==referrer_url: "+referrer_url);

        if (redirectUrl.indexOf(referrer_url) == -1) {
            redirectUrl = referrer_url + redirectUrl;
        }
        if(redirectUrl.indexOf("?") == -1) {
            redirectUrl += "?";
        }


        TgCookieGenerator cookieGenerator = new TgCookieGenerator();

        if (tgMode.equals("M-1")) {
            tgMid = cookieGenerator.getGeneratorTgCookievalue();
        }
        tgId = cookieGenerator.getGeneratorTgCookievalue();

        redirectUrl += "_tg_mode="+tgMode+"&_tg_mid="+tgMid+"&_tg_id="+tgId+"&_tg_st=Y";

        Map<String, String> returnVal = new HashMap<String, String>();
        returnVal.put("_tg_mid", tgMid);
        returnVal.put("_tg_id", tgId);
        returnVal.put("_tg_st", "Y");
        returnVal.put("redirect_url", redirectUrl);

        return returnVal;
    }

    public void redirectPage(HttpServletResponse response, String redirectUrl) throws IOException {
        response.setStatus(302);
        response.sendRedirect(redirectUrl);
    }
}
