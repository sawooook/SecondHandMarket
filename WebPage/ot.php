<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Carousel Template for Bootstrap</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

 <link href="justified-nav.css" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>


    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>

    <link href="carousel.css" rel="stylesheet">
    <meta charset="utf-8">

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
     div#image{
       padding: 0px 0px 0px 380px;
       float: left;
       font-size: 30px;
       font-weight: bold;
     }
     div#center{
       padding: 0px 5px 5px 380px;
       text-align: left;

       font-size: 16px;
       line-height: 200%
     }
     div#center2{
       padding: 20px 20px 0px 380px;
       text-align: left;
       font-size: 38px;
       font-weight:bold;

     }

     li{

     font-size: 20px;

     list-style: none;
     }
     li a{

     color: black;
     }
  #navv ul { list-style: none; width: 300px; margin: 0 auto; padding: 0; }
 #navv { float: right; }
#navv li a{display: block; padding: 16px 15px; text-decoration: none; font-size: 13px; color: #fff; border-right: 1px ;}
#navv li a:hover { color: #c00; background-color: #000; }
  #navv li:first-child a { border-left: none; }

  #mp4{

  padding: 0px 0px 0px 0px;
  }

</style>


    <title>인강맛보기</title>
  </head>
  <body>

        <div class="navbar-wrapper">



            <nav class="navbar-inverse navbar-static-top">
              <div class="container">

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

                    </form>


                    </li>
                  </ul>
                </div>
              </div>
            </nav>


        </div>



        <div id="wrap">
          <img src="p12.png" height="225.99px">
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
                      <li><a href="product_cart.php">장바구니</a></li>
                    </ul>

                  </div>

              </nav>     <!-- That's it! -->

    <div id="center2">

        한방한국사 강력한 맛보기
      </div><br>


    <div id="center">
      우리나라 한국사의 위상을 살리기 위해서 탄생하였습니다
    </div>
    <div class="container">
    <hr>
    </div>

    </div>
      <div id="center">

        학교 교육에서 한국사의 위상은 날로 추락하고 있는데, 주변 국가들은 역사교과서를 왜곡하고 심지어 역사 전쟁을 도발하고 있습니다. 한국사의 위상을 바르게 확립하는 것이 <br>
        무엇보다 시급한 실정입니다. 이러한 현실에서 우리역사에 관한 패러다임의 혁신과 한국사교육의 위상을 강화하기 위하여 국사편찬위원회에서는 한국사능력검정시험을 마련하였 <br>
        습니다. 국사편찬위원회는 우리 역사에 대한 관심을 제고하고, 한국사 전반에 걸쳐 역사적 사고력을 평가하는 다양한 유형의 문항을 개발하고 있습니다. 이를 통해 한국사 <br>
        교육의 올바른 방향을 제시하고, 자발적 역사학습을 통해 고차원적 사고력과 문제해결 능력을 배양하고자 합니다.
        <br>
<br><br><br>
    </div>
    <div id="image">

      <img src="index.png" height="30px" width="10px"> 한방한국사 간단 OT
      <br>
    </div><br><br><br><br><br><br>
    <div class="container">



        <!-- Carousel
        ================================================== -->

    <video width="1000" height="500" controls>
    <source src="saouk.mp4" type="video/mp4"> </video>
<br><br><br><br><br>
<br><br><br><br><br>
</div>

<div id="image">
  <img src="index.png" height="30px" width="10px"> 한방한국사 초단기 커리큘럼
</div><br><br><br><br><br><br>
  <div class="container">
<img src="p9.png" >
<br><br><br><br><br>
<h2>아직도 고민중이십니까?</h3>
<br><br><br><br><br>
</div>
  </body>
</html>
