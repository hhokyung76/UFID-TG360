package com.tg360.ufidtg360.app.controller;

import com.tg360.ufidtg360.common.support.EnumCookieIdType;
import com.tg360.ufidtg360.common.support.TgCookieGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class ResponseHeaderServlet {
    @RequestMapping("/request-mvc")
    public void service(HttpServletResponse response) throws IOException {

        redirect(response);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html
        //response.setStatus(HttpServletResponse.SC_FOUND); //302

        TgCookieGenerator cookieGenerator = new TgCookieGenerator();
        String tgMid = cookieGenerator.getGeneratorTgCookievalue();
        String tgId = cookieGenerator.getGeneratorTgCookievalue();
        /*

    MUID("_tg_mid"),
    TUID("_tg_id"),
    STID("_tg_st"),
         */
        Cookie cookieTgMid = new Cookie("_tg_mid", tgMid);
        cookieTgMid.setMaxAge((86400*365)*20);
        cookieTgMid.setPath("/");
        response.addCookie(cookieTgMid);

        Cookie cookieTgId = new Cookie("_tg_id", tgId);
        cookieTgId.setMaxAge((86400*365)*20);
        cookieTgId.setPath("/");
        response.addCookie(cookieTgId);

        Cookie cookieTgSt = new Cookie("_tg_st", "Y");
        cookieTgSt.setMaxAge((86400*365)*20);
        cookieTgSt.setPath("/");
        response.addCookie(cookieTgSt);

        response.setStatus(HttpServletResponse.SC_FOUND); //302
        response.sendRedirect("/index");
    }
}
