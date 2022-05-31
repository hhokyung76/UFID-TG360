<html>
<header>
<script src="js/ufid-tg360-V2.js"></script>
<script>
    window.onload = function () {
        var url = document.location.href;
        // alert(window.navigator.userAgent);
        var userAgent = window.navigator.userAgent;

        var queryString = window.location.search;
        var parameters = new URLSearchParams(queryString);

        console.log(parameters.get('_tg_mode'));
        console.log(parameters.get('_tg_mid'));
        console.log(parameters.get('_tg_id'));
        console.log(parameters.get('_tg_st'));

        var tgMode = parameters.get('_tg_mode');
        var tgMid = parameters.get('_tg_mid');
        var tgId = parameters.get('_tg_id');
        var redirectUrl = parameters.get('redirect_url');
        var referrerUrl = parameters.get('referrer_url');

        var tgufidurl = ufidMod.uf_server_host+"/tgunfiedid?_tg_mode="+tgMode+"&_tg_mid="+tgMid+"&_tg_id="+tgId+"&redirect_url="+redirectUrl+"&referrer_url="+referrerUrl+"&user_agent="+userAgent;

        // userAgent 특수문자 문제로 encodeURI로 파라메터 부분을 encoding처리함.
        var encodedUrl = encodeURI(tgufidurl);
        ufidMod.ajaxCall(encodedUrl, function (xhr) {
            var res = null;
            try {
                res = JSON.parse(xhr.responseText);
            } catch (e) {
                _mAds.log("json parse error.");
            }

            console.log("xhr.responseText: "+xhr.responseText);

            if (res && res.status) {
                console.log("--------------------");
                /*tg360 domain set-cookie

        ("_tg_mid", tgMid);
        ("_tg_id", tgId);
        ("_tg_st", "Y");
        ("redirect_url", redirectUrl);
                 */
                res && res._tg_mid && ufidMod.setCookie("_tg_mid", res._tg_mid, 1);
                res && res._tg_id && ufidMod.setCookie("_tg_id", res._tg_id, 1);
                ufidMod.setCookie("_tg_st", "Y", 1);

                window.location = res.redirect_url;
            }
        });
        //document.getElementById('here').innerHTML = data.name;
    }


</script>
</header>
<body>
</body>
</html>