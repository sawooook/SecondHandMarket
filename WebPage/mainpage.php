

<!DOCTYPE html>
<html lang="en">
  <head>


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


  .nav-justified {
    width: 100%;
    border-bottom: 0;

  }
  .nav-justified > li {
    float: none;
  }
  .nav-tabs.nav-justified > li > a {
    margin-bottom: 5px;
    text-align: center;
  }
  .nav-tabs.nav-justified > .dropdown .dropdown-menu {
    top: auto;
    left: auto;
  }
  @media (min-width: 768px) {
    .nav-tabs.nav-justified > li {
      display: table-cell;
      width: 1%;
    }
    .nav-tabs.nav-justified > li > a {
      margin-bottom: 0;
    }
  }
  .nav-tabs.nav-justified > li > a {
    margin-right: 0;
    border-radius: 4px;
  }
  .nav-tabs.nav-justified > .active > a,
  .nav-tabs.nav-justified > .active > a:hover,
  .nav-tabs.nav-justified > .active > a:focus {
    border: 1px solid #ddd;
  }
  @media (min-width: 768px) {
    .nav-tabs.nav-justified > li > a {
      border-bottom: 1px solid #ddd;
      border-radius: 4px 4px 0 0;
    }
    .nav-tabs.nav-justified > .active > a,
    .nav-tabs.nav-justified > .active > a:hover,
    .nav-tabs.nav-justified > .active > a:focus {
      border-bottom-color: #fff;
    }
  }
 #topMenu { height: 100%;  width: 100%; } #topMenu ul li { list-style: none;  color: white;  background-color: #2d2d2d; float: left; line-height: 30px;  vertical-align: middle;  text-align: center; // 글씨 정렬을 가운데로 설정 }
   #topMenu .menuLink { text-decoration:none;  color: white;  display: block;  width: 150px; font-size: 12px;정 font-weight: bold;  font-family: "Trebuchet MS", Dotum, Arial;  }
   #topMenu .menuLink:hover {  color: red; background-color: #4d4d4d; }
   li{

   font-size: 20px;

   list-style: none;
   }
   li a{

   color: black;
   }



</style>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>한방한국사</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">



    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>

    <link href="carousel.css" rel="stylesheet">
  </head>
<!-- NAVBAR
================================================== -->
<body onload="popup()">

    <script type="text/javascript" src="js/bootstrap.js"></script>





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



                </li>
              </ul>
            </div>
          </div>
        </nav>




    <div id="wrap">

  <img src="p12.png" height="225.99px">

        <br>

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


<br><br><br>



    <!-- Carousel
    ================================================== -->

<p style="text-align:center;"/>

  <img src="t2.png" width="1200"/>

<p></p>
<p></p>

  <!-- /.carousel -->



    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->
<br><br><br><br><br>
    <div class="container marketing">
    <hr>
    <br><br>


          <h2 class="featurette-heading">이번년도 한국사 시험을 위한 필수강의 <span class="text-muted"></span></h2>
          <p class="lead">매시험마다 나오는 필수 키워드 총정리 이것만 들어도 합격!</p>


<br>

<div class="col-lg-4">
  <h3>[한국사 이거하나면 게임끝]</h3>
  <a href="ot.php"><img class="img-responsive" src="p1.png" width="400" height="200" /></a>
  <p></p>
<h5>한국사 학습시간과 암기시간 단축 비법</h5>

<h5><strong>바로 이강의 하나면 게임 끝이다</strong></h5>

<br>
<br>
</div>


<div class="col-lg-4">
  <h3>[아니 이것이 개념?]</h3>
  <img class="img-responsive" src="p2.png" width="400" height="200" />
  <p></p>
  <h5>한국사 개념 총정리 입문자라면 꼭 들어야하는강의</h5>
<br>
<br>
</div>

<div class="col-lg-4">
  <h3>[한국사 쪽집게 파이널]</h3>
  <img class="img-responsive" src="p3.png" width="400" height="200" />
  <p></p>
  <h5>시험보기전에 이것만은 꼭보고 가자!</h5>
<br>
<br>
</div>


<br>
<br>
<br>

</div>
<div class="container">
<hr>
</div>

</div>


<br>
<br>
<br>





  </body>
</html>
