/*
 * A JavaScript implementation of the RSA Data Security, Inc. MD5 Message
 * Digest Algorithm, as defined in RFC 1321.
 * Version 2.2 Copyright (C) Paul Johnston 1999 - 2009
 * Other contributors: Greg Holt, Andrew Kepert, Ydnar, Lostinet
 * Distributed under the BSD License
 * See http://pajhome.org.uk/crypt/md5 for more info.
 */

/*
 * Configurable variables. You may need to tweak these to be compatible with
 * the server-side, but the defaults work in most cases.
 */
var hexcase = 0;   /* hex output format. 0 - lowercase; 1 - uppercase        */
var b64pad  = "";  /* base-64 pad character. "=" for strict RFC compliance   */

/*
 * These are the functions you'll usually want to call
 * They take string arguments and return either hex or base-64 encoded strings
 */
function hex_md5(s)    { return rstr2hex(rstr_md5(str2rstr_utf8(s))); }

/*
 * Perform a simple self-test to see if the VM is working
 */
function md5_vm_test()
{
    return hex_md5("abc").toLowerCase() == "900150983cd24fb0d6963f7d28e17f72";
}

/*
 * Calculate the MD5 of a raw string
 */
function rstr_md5(s)
{
    return binl2rstr(binl_md5(rstr2binl(s), s.length * 8));
}

/*
 * Convert a raw string to a hex string
 */
function rstr2hex(input)
{
    try { hexcase } catch(e) { hexcase=0; }
    var hex_tab = hexcase ? "0123456789ABCDEF" : "0123456789abcdef";
    var output = "";
    var x;
    for(var i = 0; i < input.length; i++)
    {
        x = input.charCodeAt(i);
        output += hex_tab.charAt((x >>> 4) & 0x0F)
            +  hex_tab.charAt( x        & 0x0F);
    }
    return output;
}

/*
 * Encode a string as utf-8.
 * For efficiency, this assumes the input is valid utf-16.
 */
function str2rstr_utf8(input)
{
    var output = "";
    var i = -1;
    var x, y;

    while(++i < input.length)
    {
        /* Decode utf-16 surrogate pairs */
        x = input.charCodeAt(i);
        y = i + 1 < input.length ? input.charCodeAt(i + 1) : 0;
        if(0xD800 <= x && x <= 0xDBFF && 0xDC00 <= y && y <= 0xDFFF)
        {
            x = 0x10000 + ((x & 0x03FF) << 10) + (y & 0x03FF);
            i++;
        }

        /* Encode output as utf-8 */
        if(x <= 0x7F)
            output += String.fromCharCode(x);
        else if(x <= 0x7FF)
            output += String.fromCharCode(0xC0 | ((x >>> 6 ) & 0x1F),
                0x80 | ( x         & 0x3F));
        else if(x <= 0xFFFF)
            output += String.fromCharCode(0xE0 | ((x >>> 12) & 0x0F),
                0x80 | ((x >>> 6 ) & 0x3F),
                0x80 | ( x         & 0x3F));
        else if(x <= 0x1FFFFF)
            output += String.fromCharCode(0xF0 | ((x >>> 18) & 0x07),
                0x80 | ((x >>> 12) & 0x3F),
                0x80 | ((x >>> 6 ) & 0x3F),
                0x80 | ( x         & 0x3F));
    }
    return output;
}

/*
 * Encode a string as utf-16
 */
function str2rstr_utf16le(input)
{
    var output = "";
    for(var i = 0; i < input.length; i++)
        output += String.fromCharCode( input.charCodeAt(i)        & 0xFF,
            (input.charCodeAt(i) >>> 8) & 0xFF);
    return output;
}

function str2rstr_utf16be(input)
{
    var output = "";
    for(var i = 0; i < input.length; i++)
        output += String.fromCharCode((input.charCodeAt(i) >>> 8) & 0xFF,
            input.charCodeAt(i)        & 0xFF);
    return output;
}

/*
 * Convert a raw string to an array of little-endian words
 * Characters >255 have their high-byte silently ignored.
 */
function rstr2binl(input)
{
    var output = Array(input.length >> 2);
    for(var i = 0; i < output.length; i++)
        output[i] = 0;
    for(var i = 0; i < input.length * 8; i += 8)
        output[i>>5] |= (input.charCodeAt(i / 8) & 0xFF) << (i%32);
    return output;
}

/*
 * Convert an array of little-endian words to a string
 */
function binl2rstr(input)
{
    var output = "";
    for(var i = 0; i < input.length * 32; i += 8)
        output += String.fromCharCode((input[i>>5] >>> (i % 32)) & 0xFF);
    return output;
}

/*
 * Calculate the MD5 of an array of little-endian words, and a bit length.
 */
function binl_md5(x, len)
{
    /* append padding */
    x[len >> 5] |= 0x80 << ((len) % 32);
    x[(((len + 64) >>> 9) << 4) + 14] = len;

    var a =  1732584193;
    var b = -271733879;
    var c = -1732584194;
    var d =  271733878;

    for(var i = 0; i < x.length; i += 16)
    {
        var olda = a;
        var oldb = b;
        var oldc = c;
        var oldd = d;

        a = md5_ff(a, b, c, d, x[i+ 0], 7 , -680876936);
        d = md5_ff(d, a, b, c, x[i+ 1], 12, -389564586);
        c = md5_ff(c, d, a, b, x[i+ 2], 17,  606105819);
        b = md5_ff(b, c, d, a, x[i+ 3], 22, -1044525330);
        a = md5_ff(a, b, c, d, x[i+ 4], 7 , -176418897);
        d = md5_ff(d, a, b, c, x[i+ 5], 12,  1200080426);
        c = md5_ff(c, d, a, b, x[i+ 6], 17, -1473231341);
        b = md5_ff(b, c, d, a, x[i+ 7], 22, -45705983);
        a = md5_ff(a, b, c, d, x[i+ 8], 7 ,  1770035416);
        d = md5_ff(d, a, b, c, x[i+ 9], 12, -1958414417);
        c = md5_ff(c, d, a, b, x[i+10], 17, -42063);
        b = md5_ff(b, c, d, a, x[i+11], 22, -1990404162);
        a = md5_ff(a, b, c, d, x[i+12], 7 ,  1804603682);
        d = md5_ff(d, a, b, c, x[i+13], 12, -40341101);
        c = md5_ff(c, d, a, b, x[i+14], 17, -1502002290);
        b = md5_ff(b, c, d, a, x[i+15], 22,  1236535329);

        a = md5_gg(a, b, c, d, x[i+ 1], 5 , -165796510);
        d = md5_gg(d, a, b, c, x[i+ 6], 9 , -1069501632);
        c = md5_gg(c, d, a, b, x[i+11], 14,  643717713);
        b = md5_gg(b, c, d, a, x[i+ 0], 20, -373897302);
        a = md5_gg(a, b, c, d, x[i+ 5], 5 , -701558691);
        d = md5_gg(d, a, b, c, x[i+10], 9 ,  38016083);
        c = md5_gg(c, d, a, b, x[i+15], 14, -660478335);
        b = md5_gg(b, c, d, a, x[i+ 4], 20, -405537848);
        a = md5_gg(a, b, c, d, x[i+ 9], 5 ,  568446438);
        d = md5_gg(d, a, b, c, x[i+14], 9 , -1019803690);
        c = md5_gg(c, d, a, b, x[i+ 3], 14, -187363961);
        b = md5_gg(b, c, d, a, x[i+ 8], 20,  1163531501);
        a = md5_gg(a, b, c, d, x[i+13], 5 , -1444681467);
        d = md5_gg(d, a, b, c, x[i+ 2], 9 , -51403784);
        c = md5_gg(c, d, a, b, x[i+ 7], 14,  1735328473);
        b = md5_gg(b, c, d, a, x[i+12], 20, -1926607734);

        a = md5_hh(a, b, c, d, x[i+ 5], 4 , -378558);
        d = md5_hh(d, a, b, c, x[i+ 8], 11, -2022574463);
        c = md5_hh(c, d, a, b, x[i+11], 16,  1839030562);
        b = md5_hh(b, c, d, a, x[i+14], 23, -35309556);
        a = md5_hh(a, b, c, d, x[i+ 1], 4 , -1530992060);
        d = md5_hh(d, a, b, c, x[i+ 4], 11,  1272893353);
        c = md5_hh(c, d, a, b, x[i+ 7], 16, -155497632);
        b = md5_hh(b, c, d, a, x[i+10], 23, -1094730640);
        a = md5_hh(a, b, c, d, x[i+13], 4 ,  681279174);
        d = md5_hh(d, a, b, c, x[i+ 0], 11, -358537222);
        c = md5_hh(c, d, a, b, x[i+ 3], 16, -722521979);
        b = md5_hh(b, c, d, a, x[i+ 6], 23,  76029189);
        a = md5_hh(a, b, c, d, x[i+ 9], 4 , -640364487);
        d = md5_hh(d, a, b, c, x[i+12], 11, -421815835);
        c = md5_hh(c, d, a, b, x[i+15], 16,  530742520);
        b = md5_hh(b, c, d, a, x[i+ 2], 23, -995338651);

        a = md5_ii(a, b, c, d, x[i+ 0], 6 , -198630844);
        d = md5_ii(d, a, b, c, x[i+ 7], 10,  1126891415);
        c = md5_ii(c, d, a, b, x[i+14], 15, -1416354905);
        b = md5_ii(b, c, d, a, x[i+ 5], 21, -57434055);
        a = md5_ii(a, b, c, d, x[i+12], 6 ,  1700485571);
        d = md5_ii(d, a, b, c, x[i+ 3], 10, -1894986606);
        c = md5_ii(c, d, a, b, x[i+10], 15, -1051523);
        b = md5_ii(b, c, d, a, x[i+ 1], 21, -2054922799);
        a = md5_ii(a, b, c, d, x[i+ 8], 6 ,  1873313359);
        d = md5_ii(d, a, b, c, x[i+15], 10, -30611744);
        c = md5_ii(c, d, a, b, x[i+ 6], 15, -1560198380);
        b = md5_ii(b, c, d, a, x[i+13], 21,  1309151649);
        a = md5_ii(a, b, c, d, x[i+ 4], 6 , -145523070);
        d = md5_ii(d, a, b, c, x[i+11], 10, -1120210379);
        c = md5_ii(c, d, a, b, x[i+ 2], 15,  718787259);
        b = md5_ii(b, c, d, a, x[i+ 9], 21, -343485551);

        a = safe_add(a, olda);
        b = safe_add(b, oldb);
        c = safe_add(c, oldc);
        d = safe_add(d, oldd);
    }
    return Array(a, b, c, d);
}

/*
 * These functions implement the four basic operations the algorithm uses.
 */
function md5_cmn(q, a, b, x, s, t)
{
    return safe_add(bit_rol(safe_add(safe_add(a, q), safe_add(x, t)), s),b);
}
function md5_ff(a, b, c, d, x, s, t)
{
    return md5_cmn((b & c) | ((~b) & d), a, b, x, s, t);
}
function md5_gg(a, b, c, d, x, s, t)
{
    return md5_cmn((b & d) | (c & (~d)), a, b, x, s, t);
}
function md5_hh(a, b, c, d, x, s, t)
{
    return md5_cmn(b ^ c ^ d, a, b, x, s, t);
}
function md5_ii(a, b, c, d, x, s, t)
{
    return md5_cmn(c ^ (b | (~d)), a, b, x, s, t);
}

/*
 * Add integers, wrapping at 2^32. This uses 16-bit operations internally
 * to work around bugs in some JS interpreters.
 */
function safe_add(x, y)
{
    var lsw = (x & 0xFFFF) + (y & 0xFFFF);
    var msw = (x >> 16) + (y >> 16) + (lsw >> 16);
    return (msw << 16) | (lsw & 0xFFFF);
}

/*
 * Bitwise rotate a 32-bit number to the left.
 */
function bit_rol(num, cnt)
{
    return (num << cnt) | (num >>> (32 - cnt));
}

 var ufidMod = {};

 ufidMod.is_debug = true;                                              /* 디버깅 모드 : validation check */
 ufidMod.script_v = 'v0.2.1';                                          /* 스크립트 버전 */
 ufidMod.uf_server_host = 'http://localhost:8092';                          /* 광고 서버 HOST */
 //ufidMod.uf_req_url = ufidMod.uf_server_host + '/tg-unified-checker';               /* web 광고요청 url */
 ufidMod.uf_req_url = ufidMod.uf_server_host + '/next';               /* web 광고요청 url */


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

    console.log("callUnfiedModule tg_mid: "+tg_mid);
    console.log("callUnfiedModule tg_id: "+tg_id);


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

    console.log("fetchTgUfidCall _tg_mid:"+_tg_mid);
    console.log("fetchTgUfidCall _tg_id:"+_tg_id);
    console.log("fetchTgUfidCall url:"+redirectUrl);
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
        if (!ufidMod.isEmpty(_tg_id) || !ufidMod.isEmpty(_tg_st)) {
            console.log("tg_id != null && tg_id != '' ")
            window.location = redirectUrl;
        } else { // _tg_id, _tg_st 값이 널일 경우 tg360으로 request요청 하지 않음. re
            _tg_mode = "M-2";
            console.log("_tg_id is null")
            if (targetUrl.indexOf("?") == -1) {
                targetUrl += "?"
            }
            targetUrl += ("redirect_url=" + redirectUrl);
            targetUrl += ("&_tg_mode=" + _tg_mode);
            targetUrl += ("&_tg_mid=" + _tg_mid);
            targetUrl += ("&referrer_url=" + window.location.origin);
            console.log("callUfidRequest url:" + targetUrl);

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
    console.log("window.location.search: " + queryString);
    console.log("window.location.origin: " + window.location.origin);
    console.log("window.location.pathname: " + window.location.pathname);
    console.log("@@parameters: " + parameters);
    console.log("@@_tg_mid: " + parameters.get('_tg_mid'));
    console.log("@@_tg_id: " + parameters.get('_tg_id'));
    console.log("@@_tg_st: " + parameters.get('_tg_st'));

    var tg_mode_from_tg = parameters.get('_tg_mode');
    var tg_mid_from_tg = parameters.get('_tg_mid');
    var tg_id_from_tg = parameters.get('_tg_id');
    var tg_st_from_tg = parameters.get('_tg_st');

    console.log("@@--tg_mode_from_tg: " + tg_mode_from_tg);
    if (tg_mode_from_tg == 'M-1') {
        console.log("@@null check _tg_mid: " + tg_mid_from_tg);
        ufidMod.setCookie("_tg_mid", tg_mid_from_tg, 365);
        ufidMod.setCookie("_tg_id", tg_id_from_tg, 365);
        ufidMod.setCookie("_tg_st", tg_st_from_tg, 365);
    }else if (tg_mode_from_tg == 'M-2') {
        console.log("@@null check _tg_mid: " + tg_mid_from_tg);
        ufidMod.setCookie("_tg_id", tg_id_from_tg, 365);
        ufidMod.setCookie("_tg_st", tg_st_from_tg, 365);
    }

})

// }) (window);