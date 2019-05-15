
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">


    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">


    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <title>Signin Template for Bootstrap</title>


    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <style type="text/css">
      div{
        text-align: center;
      }
      h1#ss{
        background-color: : red;
    }
    div#s_left{
      text-align: center;
      width: 700px;
      height: 200px;
      background-color: yellow;
      float: left;
    }

      #nav { width: 100%; float: left; margin: 0 0 1em 0; padding: 0; background-color: #fff;  border-bottom: 1.5px solid #ccc; }
      #nav ul { list-style: none; width: 600px; margin: 0 auto; padding: 1px 5px 5px 1px; }
       #nav li { float: left; }
        #nav li a { display: block;padding: 15px 30px; margin: 8px 0px 8px 0px; text-decoration: none; font-size: 14px; font-weight: bold; color: #000; border-right: 1px solid #ccc; }
        #nav li:first-child a { border-left: 1px  solid #ccc; }
         #nav li a:hover { color: #c00; background-color: #000; }


      #navv ul { list-style: none; width: 300px; margin: 0 auto; padding: 0; }
     #navv { float: right; }
    #navv li a{display: block; padding: 16px 15px; text-decoration: none; font-size: 13px; color: #fff; border-right: 1px ;}
    #navv li a:hover { color: #c00; background-color: #000; }
      #navv li:first-child a { border-left: none; }
      li{

      font-size: 20px;

      list-style: none;
      }
      li a{

      color: black;
      }


    </style>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="navbar-wrapper">



        <nav class="navbar-inverse navbar-static-top">
          <div class="container">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>

            </div>
            <div id="navv" class="navbar-collapse collapse">
              <ul class="navbar-nav">
                <?php
                session_start();

                if(isset($_SESSION['userid'])){

                ?>
                                <li><a href="product_cart.php">장바구니</a></li>
                                <li><a href="logout.php?board">로그아웃</a></li>
                                <?php }  else{ ?>



                                <li><a href="login.php">로그인</a></li>
                                <?php }  ?>
                                <li><a href="signup.php?signup"> 회원가입</a></li>


                </li>
              </ul>
            </div>
          </div>
        </nav>


    </div>



    <div id="wrap">

            <img src="p12.png" height="226.9px">
                </div>



                <nav class="navbar navbar-default">

                    <div class="navbar-header">


                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                      <ul class="nav nav-justified">
                        <li><a href="mainpage.php">HOME</a></li>
                        <li><a href="hanguk.php?hanguksa">한국사 능력 검정시험이란?</a></li>
                        <li><a href="product.php?">수강신청</a></li>
                        <li><a href="one.php?question">후기게시판</a></li>
                        <li><a href="mypage.php?question">마이페이지</a></li>
                      </ul>

                    </div>

                </nav>



  <center>
    <div class="container">

    <legend>

  <br>

    로그인
    <br>


      <br>


    </legend>
  </div>
      <br>
      <br>
      <br>

      <form action="process_login.php" method="POST">

    <input type="text" name="loginID" required="required" size="35" placeholder="아이디를 입력해주세요"/>
  <br>
    <br>

    <input type="password" name="loginPASSWORD"  required="required" size="35" placeholder="비밀번호를 입력해주세요"/>
<br>
<br>

<br>
<br>
<br>

    <input type="submit" name="signup_button" value="Sign up" style="height:35px; width:200px;">

    </form>

<br>
<br>



  </center>
    <br>




  <br>
  <br>





  </body>
</html>
