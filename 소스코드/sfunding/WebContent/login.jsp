<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<!DOCTYPE html>
<html  549'>
<head  550'>
    <meta  551' http-equiv="content-type" content="text/html; charset=UTF-8">
    <title  552'>Split Screen Bootstrap 4 Sign In Page Example with Floating Form Labels</title>
    <meta  553' http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta  554' name="robots" content="noindex, nofollow">
    <meta  555' name="googlebot" content="noindex, nofollow">
    <meta  556' name="viewport" content="width=device-width, initial-scale=1">


    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>스펀딩 로그인</title>
    <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="/css/styles.css" rel="stylesheet" />
    <script  557' type="text/javascript" src="/js/lib/dummy.js"></script>

    <link  558' rel="stylesheet" type="text/css" href="body {
  background: red;
}">

    <script  559' type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.slim.js"></script>
    <link  560' rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script  561' type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>

    <style  562' id="compiled-css" type="text/css">
        :root {
            --input-padding-x: 1.5rem;
            --input-padding-y: 0.75rem;
        }

        .login,
        .image {
            min-height: 100vh;
        }

        .bg-image {
            background-image: url('https://source.unsplash.com/WEQbe2jBg40/600x1200');
            background-size: cover;
            background-position: center;
        }

        .login-heading {
            font-weight: 300;
        }

        .btn-login {
            font-size: 0.9rem;
            letter-spacing: 0.05rem;
            padding: 0.75rem 1rem;
            border-radius: 2rem;
        }

        .form-label-group {
            position: relative;
            margin-bottom: 1rem;
        }

        .form-label-group>input,
        .form-label-group>label {
            padding: var(--input-padding-y) var(--input-padding-x);
            height: auto;
            border-radius: 2rem;
        }

        .form-label-group>label {
            position: absolute;
            top: 0;
            left: 0;
            display: block;
            width: 100%;
            margin-bottom: 0;
            /* Override default `<label>` margin */
            line-height: 1.5;
            color: #495057;
            cursor: text;
            /* Match the input under the label */
            border: 1px solid transparent;
            border-radius: .25rem;
            transition: all .1s ease-in-out;
        }

        .form-label-group input::-webkit-input-placeholder {
            color: transparent;
        }

        .form-label-group input:-ms-input-placeholder {
            color: transparent;
        }

        .form-label-group input::-ms-input-placeholder {
            color: transparent;
        }

        .form-label-group input::-moz-placeholder {
            color: transparent;
        }

        .form-label-group input::placeholder {
            color: transparent;
        }

        .form-label-group input:not(:placeholder-shown) {
            padding-top: calc(var(--input-padding-y) + var(--input-padding-y) * (2 / 3));
            padding-bottom: calc(var(--input-padding-y) / 3);
        }

        .form-label-group input:not(:placeholder-shown)~label {
            padding-top: calc(var(--input-padding-y) / 3);
            padding-bottom: calc(var(--input-padding-y) / 3);
            font-size: 12px;
            color: #777;
        }

        /* Fallback for Edge
-------------------------------------------------- */

        @supports (-ms-ime-align: auto) {
            .form-label-group>label {
                display: none;
            }

            .form-label-group input::-ms-input-placeholder {
                color: #777;
            }
        }

        /* Fallback for IE
-------------------------------------------------- */

        @media all and (-ms-high-contrast: none),
        (-ms-high-contrast: active) {
            .form-label-group>label {
                display: none;
            }

            .form-label-group input:-ms-input-placeholder {
                color: #777;
            }
        }

        /* EOS */

        .daehong {
            overflow: visible;
            display: flex;
            align-items: center;
            justify-content: center;

        }
    </style>

    <scriptid="insert"></script>


</head>

<body>
<!-- 상단 네비게이션 -->
<jsp:include page="common/header.jsp"/>

    <div class="container-fluid">
        <div class="row no-gutter">
            <div class="d-none d-md-flex col-md-4 col-lg-6"><img src="views/assets/logo.png" style="height:100%; width:100%;object-fit:contain;"></div>
            <div class="col-md-8 col-lg-6">
                <div class="login d-flex align-items-center py-5">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-9 col-lg-8 mx-auto">
                                <h3 class="login-heading mb-4">로그인 페이지</h3>
                                 <form action="/Login" method="post">
                               <!--  <form action="/Login" method="post"  574'> -->
                                    <div class="form-label-group">
                                        <input id="userId"  type="text" name = "userId" class="form-control" placeholder="Email address" required autofocus>
                                        <label for="userId">아이디</label>
                                    </div>

                                    <div class="form-label-group">
                                        <input type="password" id="userPwd" name = "userPwd" class="form-control" placeholder="Password" required>
                                        <label for="userPwd">비밀번호</label>
                                    </div>

									
                                    <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" type="submit">로그인</button>
                                    <div  class="text-center">
                                        <a class="small" href="/views/member/findId.jsp">아이디 찾기</a></div>

                                    <div  class="text-center">
                                        <a  class="small" href="/views/member/findPwd.jsp"> 비밀번호 찾기</a></div>
                                        
                                         <div  class="text-center">
                                        <a class="small" href="/views/member/enroll.html">회원가입</a></div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- 푸터, 하단바 -->
        <jsp:include page="/common/footer.html"/>
    </div>


    <!-- TODO: Missing CoffeeScript 2 -->

    <script type="text/javascript">
        //<![CDATA[

        //]]>
    </script>

    <script>
        // tell the embed parent frame the height of the content
        if (window.parent && window.parent.parent) {
            window.parent.parent.postMessage(["resultsFrame", {
                height: document.body.getBoundingClientRect().height,
                slug: "efvg9j7a"
            }], "*")
        }

        // always overwrite window.name, in case users try to set it manually
        window.name = "result"
    </script>

</body>
</html>
