package com.tg360.ufidtg360.app.controller;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.tg360.ufidtg360.app.cookie.CookieManager;
import com.tg360.ufidtg360.app.manager.UnifiedManager;
import com.tg360.ufidtg360.common.support.EnumCookieIdType;
import com.tg360.ufidtg360.common.support.TestComponent;
import com.tg360.ufidtg360.common.support.TgCookieGenerator;
import com.tg360.ufidtg360.dto.UserAgentDTO;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua_parser.Client;
import ua_parser.Parser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Slf4j
@Controller
public class TGUFIDController {

    @Autowired
    private TestComponent testCom;

    @Autowired
    private UnifiedManager unifiedManager;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView boardListGet(ModelAndView mv) {
        mv.setViewName("index");
        //기능 코드는 생략
        return mv;
    }

    @RequestMapping(value = "/next", method = RequestMethod.GET)
    public ModelAndView next(ModelAndView mv) {
        mv.setViewName("next");
        //기능 코드는 생략
        return mv;
    }


    @RequestMapping("/dashboard")
    public String dashboard() {
        return "index";
    }

    @RequestMapping("/cookie-page")
    public String cookie_page() {
        return "cookie-page";
    }  //("Access-Control-Allow-Origin", "*");


    @RequestMapping("/tg-unified-checker")
    public String cookie_page_checker() {
        return "tg-unified-checker";
    }  //("Access-Control-Allow-Origin", "*");

    @RequestMapping(value="/set/cookie", method=RequestMethod.GET)
    public void testCookie(HttpServletResponse response){
        Cookie setCookie = new Cookie("tg-id", "tg-9000-1234"); // 쿠키 이름을 name으로 생성
        setCookie.setMaxAge(60*60*24); // 기간을 하루로 지정(60초 * 60분 * 24시간)
        response.addCookie(setCookie); // response에 Cookie 추가
    }

    @RequestMapping(value="/search/cookie", method=RequestMethod.GET)
    @ResponseBody
    public String testCookie(HttpServletRequest req){

        StringBuffer urlSb = req.getRequestURL();
        log.info("urlSb22: "+urlSb.toString());

        List<Map<String, Object>> cookieList = new ArrayList<Map<String, Object>>();
        Cookie[] getCookie = req.getCookies(); // 모든 쿠키 가져오기
        if(getCookie != null){ // 만약 쿠키가 없으면 쿠키 생성
            for(int i=0; i<getCookie.length; i++){
                Cookie c = getCookie[i]; // 객체 생성
                String domain = c.getDomain();
                String name = c.getName(); // 쿠키 이름 가져오기
                String value = c.getValue(); // 쿠키 값 가져오기
                Map<String, Object> cookieInfo = new HashMap<String, Object>();
                cookieInfo.put("domain", domain);
                cookieInfo.put("name", name);
                cookieInfo.put("value", value);
                cookieList.add(cookieInfo);

                log.info("cookie: "+c.toString());
                log.info("CookieInfo => domain["+domain+"] name["+name+"] value["+value+"]");
            }
        }
        String json = new Gson().toJson(cookieList);
        return json;
    }

   // @RequestMapping("/tgufied")



    /**
     * 광고주 페이지에서 tg_mid가 없을 시 생성 요청.
     * 20220523
     * hk.lee
     * @param request
     * @param response
     */
    @RequestMapping(value="/tgm", method=RequestMethod.GET)
    @ResponseBody
    public void tgUnifiedUrl(HttpServletRequest request, HttpServletResponse response) {

        String referrer = request.getHeader("referer");
        //String tgMidReq = request.getParameter("_tg_mid");

        referrer = Strings.nullToEmpty(referrer);

        log.info("!!referrer : "+referrer);

        TgCookieGenerator cookieGenerator = new TgCookieGenerator();
        String tgMid = cookieGenerator.getGeneratorTgCookievalue();

        JsonObject jsonObj = new JsonObject();
        jsonObj.put("tgm", tgMid);
    }

    /**
     * _tg_mid와 _tg_id를 파라메터로 받아 값이 없을 경우
     * 해당 쿠키값을 생성
     * 생성 후 redirect_url을 반환하여 페이지 변환 시킨다.
     * 20220523
     * hk.lee
     * @param request
     * @param response
     */
    @RequestMapping(value="/tgunfiedid", method=RequestMethod.GET)
    @ResponseBody
    public String tgUnifiedUrl2(HttpServletRequest request, HttpServletResponse response) {

        String tgModeReq = request.getParameter("_tg_mode");
        String tgMidReq = request.getParameter("_tg_mid");
        String tgIdReq = request.getParameter("_tg_id");
        String redirectUrlStr = request.getParameter("redirect_url");
        String referrerUrlStr = request.getParameter("referrer_url");
        String userAgent = request.getParameter("user_agent");
        log.info("!!tgUnifiedUrl redirectUrlStr: "+redirectUrlStr);



        tgMidReq = Strings.nullToEmpty(tgMidReq);
        tgIdReq = Strings.nullToEmpty(tgIdReq);

        Map<String, String> tgRequestInfo = new HashMap<>();
        tgRequestInfo.put("_tg_mode", tgModeReq);
        tgRequestInfo.put("_tg_mid", tgMidReq);
        tgRequestInfo.put("_tg_id", tgIdReq);
        tgRequestInfo.put("redirect_url", redirectUrlStr);
        tgRequestInfo.put("referrer_url", referrerUrlStr);
        CookieManager cookieManager = new CookieManager();
        Map<String, String> tgCookieInfo = cookieManager.buildTgCookieInfoReal(tgRequestInfo);

        String _tgMid = tgCookieInfo.get("_tg_mid");
        String _tgId = tgCookieInfo.get("_tg_id");

        Parser uaParser = null;
        try {
            uaParser = unifiedManager.getUserAgentParserPool().borrowObject();
            Client userAgentParse = uaParser.parse(userAgent);

            String browser = userAgentParse.userAgent.family;
            String browserVersion = userAgentParse.userAgent.major + "." + userAgentParse.userAgent.minor;

            String os = userAgentParse.os.family;
            String osVersion = userAgentParse.os.major + "." + userAgentParse.os.minor;

            String device = userAgentParse.device.family;

            UserAgentDTO userAgentInfo = new UserAgentDTO.Builder()
                    .browser(browser)
                    .browserVersion(browserVersion)
                    .os(os)
                    .osVersion(osVersion)
                    .device(device)
                    .tgMode(tgModeReq)
                    .tgMid(_tgMid)
                    .tgId(_tgId)
                    .build();

            Gson gson = new Gson();
            String jsonGson = gson.toJson(userAgentInfo);
            log.info("jsonGson useragentInfo: "+jsonGson);

            unifiedManager.getUserAgentParserPool().returnObject(uaParser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson returnJson = new Gson();
        tgCookieInfo.put("status", "200");
        String returnJsonStr = returnJson.toJson(tgCookieInfo);
        log.info("returnJsonStr: "+returnJsonStr);

        return returnJsonStr;

    }

    @RequestMapping(value="/test/speed", method=RequestMethod.GET)
    public void testResponse(HttpServletResponse response) throws IOException {

        log.info("testResponse called....");

        LinkedHashMap<String, JsonObject> products = testCom.getProducts();

        JsonArray arr = new JsonArray();
        products.forEach((k, v) -> arr.add(v));
        response.setContentType("application/json");
        response.setStatus(200);
        PrintWriter out = response.getWriter();
        out.print(arr.encodePrettily());
    }
}

