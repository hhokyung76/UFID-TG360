var ufidMod = {};

ufidMod.is_debug = true;                                            /* 디버깅 모드 : validation check */
ufidMod.script_v = 'v0.2';                                          /* 스크립트 버전 */
ufidMod.uf_server_host = 'http://uid.tg360.com:8092';                    /*  서버 HOST */
//ufidMod.uf_server_host = 'http://172.16.0.25:8092';                    /*  서버 HOST */
 //ufidMod.uf_req_url = ufidMod.uf_server_host + '/tg-unified-checker';
ufidMod.uf_tgm_req_url = ufidMod.uf_server_host + '/tgm';               /* tgm 요청 url */
ufidMod.uf_req_url = ufidMod.uf_server_host + '/next';               /* 요청 url */


 /* 로그 */
 ufidMod.log = function (msg){
     if (ufidMod.is_debug) {
         console.log(msg);
     }
 };

 /* 에러 로그 */
ufidMod.errorLog = function (msg){
     if(ufidMod.is_debug){
         console.log(msg);
         //throw new Error(msg);
     }
 };

ufidMod.setCookie = function(name, value, day) {
    var date = new Date();
    date.setTime(date.getTime() + day * 60 * 60 * 24 * 1000);
    document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
};

ufidMod.getCookie = function(name) {
    var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    return value? value[2] : null;
};

ufidMod.deleteCookie = function(name) {
    var date = new Date();
    document.cookie = name + "= " + "; expires=" + date.toUTCString() + "; path=/";
}


ufidMod.callUnfiedModule = function(redirectUrl) {
    var tg_mid = ufidMod.getCookie("_tg_mid");
    var tg_id = ufidMod.getCookie("_tg_id");

    ufidMod.log("callUnfiedModule tg_mid: "+tg_mid);
    ufidMod.log("callUnfiedModule tg_id: "+tg_id);


    var url = ufidMod.uf_req_url;
    url += ("redirect=" + redirectUrl);
   // if (tg_mid == null || tg_mid == "") {
    //ufidMod.callUfidRequest(redirectUrl+"?redirect_url="+redirectUrl+"&_tg_mid="+tg_mid+"&_tg_id="+tg_id);
    ufidMod.callUfidRequest(redirectUrl, tg_mid, tg_id);
    //}
}

/* ajax 호출 - 광고요청. */
ufidMod.ajaxCall = function (url, successFunc) {

    ufidMod.log("AjaxCall : " + url);

    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {

        if (xhr.readyState === xhr.DONE) {

            successFunc(xhr);

        }
    };

    xhr.open('GET', url,true);
    xhr.withCredentials = true;
    /* xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); */
    xhr.send();
};


ufidMod.isEmpty = function(value) {
    if(value == null || value.length === 0) {
        return true;
    } else{
        return false;
    }
}


/*unifiedId 생성 call*/
ufidMod.tgUfidCall = function(redirectUrl) {

    var _tg_mode = "M-1"; // [M-1] : ALL, [M-2] : TG_ID생성용.
    var _tg_mid = ufidMod.getCookie("_tg_mid");
    var _tg_id = ufidMod.getCookie("_tg_id");
    var _tg_st = ufidMod.getCookie("_tg_st");

    var targetUrl = ufidMod.uf_req_url;

    ufidMod.log("tgUfidCall _tg_mid:"+_tg_mid);
    ufidMod.log("tgUfidCall _tg_id:"+_tg_id);
    ufidMod.log("tgUfidCall url:"+redirectUrl);
    if (ufidMod.isEmpty(_tg_mid)) {  // _tg_mid 가 널일 경우.
        _tg_mode = "M-1";
        if (targetUrl.indexOf("?") == -1) {
            targetUrl += "?"
        }
        targetUrl += ("redirect_url=" + redirectUrl);
        targetUrl += ("&_tg_mode=" + _tg_mode);
        targetUrl += ("&_tg_mid=" + _tg_mid);
        targetUrl += ("&referrer_url=" + window.location.origin);
        console.log("callUfidRequest url:" + targetUrl);

        window.location = targetUrl;
    }else {  // _tg_mid 값이 널이 아닐 경우.
        // _tg_id, _tg_st 값이 널이 아닐 경우 tg360으로 request요청 하지 않음. re
        if (!ufidMod.isEmpty(_tg_id) && !ufidMod.isEmpty(_tg_st)) {
            ufidMod.log("tg_id != null && tg_id != '' ")
            window.location = redirectUrl;
        } else { // _tg_id, _tg_st 값이 널일 경우 tg360으로 request요청 하지 않음. re
            _tg_mode = "M-2";
            ufidMod.log("_tg_id is null")
            if (targetUrl.indexOf("?") == -1) {
                targetUrl += "?"
            }
            targetUrl += ("redirect_url=" + redirectUrl);
            targetUrl += ("&_tg_mode=" + _tg_mode);
            targetUrl += ("&_tg_mid=" + _tg_mid);
            targetUrl += ("&referrer_url=" + window.location.origin);
            ufidMod.log("callUfidRequest url:" + targetUrl);

            window.location = targetUrl;
        }
    }


};


/**
 * 페이지 로딩된 후 처리 함수.
 * 20220524
 * hk.lee
 */
window.addEventListener('DOMContentLoaded', function() {
    //실행될 코드

    var _tg_mid = ufidMod.getCookie("_tg_mid");
    var queryString = window.location.search;
    var parameters = new URLSearchParams(queryString);
    ufidMod.log("window.location.search: " + queryString);
    ufidMod.log("window.location.origin: " + window.location.origin);
    ufidMod.log("window.location.pathname: " + window.location.pathname);
    ufidMod.log("@@parameters: " + parameters);
    ufidMod.log("@@_tg_mid: " + parameters.get('_tg_mid'));
    ufidMod.log("@@_tg_id: " + parameters.get('_tg_id'));
    ufidMod.log("@@_tg_st: " + parameters.get('_tg_st'));

    var tg_mode_from_tg = parameters.get('_tg_mode');
    var tg_mid_from_tg = parameters.get('_tg_mid');
    var tg_id_from_tg = parameters.get('_tg_id');
    var tg_st_from_tg = parameters.get('_tg_st');

    console.log("@@--tg_mode_from_tg: " + tg_mode_from_tg);
    if (tg_mode_from_tg == 'M-1') {
        ufidMod.log("@@null check _tg_mid: " + tg_mid_from_tg);
        ufidMod.setCookie("_tg_mid", tg_mid_from_tg, 365);
        ufidMod.setCookie("_tg_id", tg_id_from_tg, 365);
        ufidMod.setCookie("_tg_st", tg_st_from_tg, 365);
    }else if (tg_mode_from_tg == 'M-2') {
        ufidMod.log("@@null check _tg_mid: " + tg_mid_from_tg);
        ufidMod.setCookie("_tg_id", tg_id_from_tg, 365);
        ufidMod.setCookie("_tg_st", tg_st_from_tg, 365);
    }

})

// }) (window);