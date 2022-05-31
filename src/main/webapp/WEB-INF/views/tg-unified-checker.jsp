<%@ page import="com.google.common.base.Strings" %>
<%@ page import="com.tg360.ufidtg360.app.cookie.CookieManager" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<header>
</header>
<body>
subway good page
<script>
    alert('gogo');
</script>
<script src="js/ufid-tg360-V2.js"></script>
<%
    String url = null;
    //선택한 사이트를 String으로 받아온다.
    String tgMode = Strings.nullToEmpty(request.getParameter("_tg_mode"));
    String tgMid = Strings.nullToEmpty(request.getParameter("_tg_mid"));
    String tgId = Strings.nullToEmpty(request.getParameter("_tg_id"));
    String redirectUrl = Strings.nullToEmpty(request.getParameter("redirect_url"));
    System.out.println("==##tgMid=> "+tgMid);
    System.out.println("==##tgId=> "+tgId);

    CookieManager cookieManager = new CookieManager();
    Map<String, String> tgCookieInfo = cookieManager.buildTgCookieInfo(request);
    System.out.println("##_tg_mid=> "+tgCookieInfo.get("_tg_mid"));
    System.out.println("##_tg_id=> "+tgCookieInfo.get("_tg_id"));
    System.out.println("##redirect_url=> "+tgCookieInfo.get("redirect_url"));

    if (tgMode.equals("M-1")) {
        tgMid = tgCookieInfo.get("_tg_mid");
    }
    redirectUrl = tgCookieInfo.get("redirect_url");

    StringBuffer scriptSB = new StringBuffer();
    scriptSB.append("<script type='text/javascript'>");
    scriptSB.append("ufidMod.setCookie('_tg_mid2', '"+tgMid+"', 365);");
    scriptSB.append("ufidMod.setCookie('_tg_id2', '"+tgCookieInfo.get("_tg_id")+"', 365);");
    scriptSB.append("ufidMod.setCookie('_tg_st2', 'Y', 365);");
    scriptSB.append("</script>");
    System.out.println("script: "+scriptSB.toString());
    out.println(scriptSB.toString());

    Thread.sleep(1000);
    System.out.println("----response.sendRedirect: "+redirectUrl);
    cookieManager.redirectPage(response, redirectUrl);
    //response.setStatus(302);
    //response.sendRedirect(redirectUrl);
%>
test
</body>
</html>