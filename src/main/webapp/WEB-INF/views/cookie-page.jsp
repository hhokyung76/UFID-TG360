<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<head>
    <title>DashboardKit Bootstrap 5 Admin Template</title>
    <!-- HTML5 Shim and Respond.js IE11 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 11]>
    	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    	<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    	<![endif]-->
    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="description" content="DashboardKit is made using Bootstrap 5 design framework. Download the free admin template & use it for your project.">
    <meta name="keywords" content="DashboardKit, Dashboard Kit, Dashboard UI Kit, Bootstrap 5, Admin Template, Admin Dashboard, CRM, CMS, Free Bootstrap Admin Template">
    <meta name="author" content="DashboardKit ">


    <!-- Favicon icon -->
    <link rel="icon" href="assets/images/favicon.svg" type="image/x-icon">

    <!-- font css -->
    <link rel="stylesheet" href="assets/fonts/feather.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome.css">
    <link rel="stylesheet" href="assets/fonts/material.css">

    <!-- vendor css -->
    <link rel="stylesheet" href="assets/css/style.css" id="main-style-link">
	<link href="https://unpkg.com/gridjs/dist/theme/mermaid.min.css" rel="stylesheet"/>
    <script src="https://code.jquery.com/jquery-3.6.0.slim.js" integrity="sha256-HwWONEZrpuoh951cQD1ov2HUK5zA5DwJ1DNUXaM6FsY=" crossorigin="anonymous"></script>
</head>
<script src="js/ufid-tg360-V2.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/core.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/md5.js"></script>
<script type="text/javascript">
    var getCookieForTg = function() {
        ufidMod.log("called getCookieForTg");
        alert("_tg_mid=>"+ufidMod.getCookie("_tg_mid"));
        alert("_tg_id=>"+ufidMod.getCookie("_tg_id"));
        alert("_tg_st=>"+ufidMod.getCookie("_tg_st"));
    }
    var deleteCookies = function() {
        ufidMod.log("called deleteCookies");
        ufidMod.deleteCookie("_tg_mid");
        ufidMod.deleteCookie("_tg_id");
        ufidMod.deleteCookie("_tg_st");
    }

    var redirectCall = function() {
        ufidMod.log("called redirect");
    }

    var fetchTgUfidCall = function(redirectUrl) {
        ufidMod.tgUfidCall(redirectUrl);
    }

    var md5Test2 = function() {
        var text = document.getElementById("md5-text").value;
        console.log("text: "+text);
        var digest = hex_md5(text);
        console.log("hex_md5: "+digest.toUpperCase());

        var md5Text = CryptoJS.MD5(text).toString();
        console.log("md5-cryptoJS: "+md5Text);
        //35454b055cc325ea1af2126e27707052
        //35454B055CC325EA1AF2126E27707052
    }


    /**
     * UUID 생성 위한 전처리 작업 20220526...
     */
    var md5Test = function() {
        var text = document.getElementById("md5-text").value;
        console.log("text: "+text);
        var digest = hex_md5(text);
        console.log("hex_md5: "+digest.toUpperCase());

        var timeStamp = Math.round(new Date().valueOf()/1000);
        console.log("timeStamp: "+timeStamp);

        var referrer = new URL(document.referrer).hostname;
        var arr = Array(40).fill().map(() => Math.round(Math.random() * 122)).filter(number => (number >= 48) && (number <= 57 || number >= 65) && (number <= 90 || number >= 97)).slice(1, 8)
        var generatedStr = "";
        for (i in arr){
            generatedStr+=String.fromCharCode(arr[i]);
        }

        var plainText = referrer+"|"+timeStamp+"|"+generatedStr;
        console.log("plainText: "+plainText);
        // var host = window.document.origin.toString();
        console.log("host: "+referrer);
        console.log("uuidv4: "+uuidv4());

        const INIT_NUMBER = 271;

        function hash(uuid, N) {
            const x = uuid.split("-").reduce((a,b) => a ^ Number.parseInt(b, 16), INIT_NUMBER) ;
            return arguments.length === 1 ? x : x % N;
        }

        const a = hash("dcc549d8-bd0c-49c2-bff8-f6fa80fb7857");
        const b = hash("dcc549d8-bd0c-49c2-bff8-f6fa80fb7857", 256);

        console.log("a-="+a);
        console.log("generateUUID(): "+generateUUID());

        var lastUuid = stringToUuid(plainText);
        console.log(uuid());



    }


    function uuid() {
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
            var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        });
    }
    const stringToUuid = (str) => {
        str = str.replace('-', '');
        return 'xxxxxxxx-xxxx-4xxx-xxxx-xxxxxxxxxxxx'.replace(/[x]/g, function(c, p) {
            return str[p % str.length];
        });
    }

    function uuidv4() {
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
            var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        });
    }

    function generateUUID() { // Public Domain/MIT
        var d = new Date().getTime();//Timestamp
        var d2 = ((typeof performance !== 'undefined') && performance.now && (performance.now()*1000)) || 0;//Time in microseconds since page-load or 0 if unsupported
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
            var r = Math.random() * 16;//random number between 0 and 16
            if(d > 0){//Use timestamp until depleted
                r = (d + r)%16 | 0;
                d = Math.floor(d/16);
            } else {//Use microseconds since page-load if supported
                r = (d2 + r)%16 | 0;
                d2 = Math.floor(d2/16);
            }
            return (c === 'x' ? r : (r & 0x3 | 0x8)).toString(16);
        });
    }
</script>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<body class="">
	<!-- [ Pre-loader ] start -->
	<div class="loader-bg">
		<div class="loader-track">
			<div class="loader-fill"></div>
		</div>
	</div>
	<!-- [ Pre-loader ] End -->
	<!-- [ Mobile header ] start -->
	<div class="pc-mob-header pc-header">
		<div class="pcm-logo">
			<img src="assets/images/logo.svg" alt="" class="logo logo-lg">
		</div>
		<div class="pcm-toolbar">
			<a href="#!" class="pc-head-link" id="mobile-collapse">
				<div class="hamburger hamburger--arrowturn">
					<div class="hamburger-box">
						<div class="hamburger-inner"></div>
					</div>
				</div>
			</a>
			<a href="#!" class="pc-head-link" id="headerdrp-collapse">
				<i data-feather="align-right"></i>
			</a>
			<a href="#!" class="pc-head-link" id="header-collapse">
				<i data-feather="more-vertical"></i>
			</a>
		</div>
	</div>
	<!-- [ Mobile header ] End -->

	<!-- [ navigation menu ] start -->
	<jsp:include page="/WEB-INF/views/navi.jsp"/>
	<!-- [ navigation menu ] end -->
	<!-- [ Header ] start -->
	<jsp:include page="/WEB-INF/views/header.jsp"/>
	<!-- [ Header ] end -->

<!-- [ Main Content ] start -->

<div class="pc-container">
    <div class="pcoded-content">
        <!-- [ breadcrumb ] start -->
        <div class="page-header">
            <div class="page-block">
                <div class="row align-items-center">
                    <div class="col-md-12">
                        <div class="page-header-title">
                            <h5 class="m-b-10">Sample Page</h5>
                        </div>
                        <ul class="breadcrumb">
                            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                            <li class="breadcrumb-item">Sample Page</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- [ breadcrumb ] end -->
        <!-- [ Main Content ] start -->
        <div class="row">
            <!-- [ sample-page ] start -->
            <div class="col-sm-12">
                <div class="card">
                    <div class="card-header">
                        <h5>Hello card223</h5>
                    </div>
                    <div class="card-body">
                        <div id="home" style="border: 1px solid gold; padding: 10px; height: auto; min-height: 100px; overflow: auto;" width="200" height="200" src="https://www.baeldung.com/spring-boot-custom-error-page"></div>
                        <button type="button" class="btn  btn-primary" id="btn" onclick="getCookieForTg();">쿠키확인</button>
                        <button type="button" class="btn  btn-secondary" onclick="ufidMod.tgUfidCall('/index');">TGUFID 테스트</button>
                        <button type="button" class="btn  btn-success" id="successBtn">Success</button>
                        <button type="button" class="btn  btn-danger" onclick="deleteCookies();">쿠키 삭제</button>
                        <button type="button" class="btn  btn-info" ><a href="/request-mvc">Redirect</a></button>
                        <button type="button" class="btn  btn-info" onclick="md5Test();">md5-test</button>
                    </div>

                    <div class="form-group">
                        <label class="form-label" for="">Value</label>
                        <input type="text" id="md5-text" class="form-control" value="ILoveJava" width="200" maxlength="20">
                    </div>
                    <div class="form-group">
                        <label class="form-label" for="">Value</label>
                        <input type="text" class="form-control" value="John Doe">
                    </div>
                </div>
            </div>
            <!-- [ sample-page ] end -->
        </div>
        <!-- [ Main Content ] end -->
    </div>
</div>
<!-- [ Main Content ] end -->
    <!-- Warning Section start -->
    <!-- Older IE warning message -->
    <!--[if lt IE 11]>
        <div class="ie-warning">
            <h1>Warning!!</h1>
            <p>You are using an outdated version of Internet Explorer, please upgrade
               <br/>to any of the following web browsers to access this website.
            </p>
            <div class="iew-container">
                <ul class="iew-download">
                    <li>
                        <a href="http://www.google.com/chrome/">
                            <img src="assets/images/browser/chrome.png" alt="Chrome">
                            <div>Chrome</div>
                        </a>
                    </li>
                    <li>
                        <a href="https://www.mozilla.org/en-US/firefox/new/">
                            <img src="assets/images/browser/firefox.png" alt="Firefox">
                            <div>Firefox</div>
                        </a>
                    </li>
                    <li>
                        <a href="http://www.opera.com">
                            <img src="assets/images/browser/opera.png" alt="Opera">
                            <div>Opera</div>
                        </a>
                    </li>
                    <li>
                        <a href="https://www.apple.com/safari/">
                            <img src="assets/images/browser/safari.png" alt="Safari">
                            <div>Safari</div>
                        </a>
                    </li>
                    <li>
                        <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie">
                            <img src="assets/images/browser/ie.png" alt="">
                            <div>IE (11 & above)</div>
                        </a>
                    </li>
                </ul>
            </div>
            <p>Sorry for the inconvenience!</p>
        </div>
    <![endif]-->
    <!-- Warning Section Ends -->

</body>

