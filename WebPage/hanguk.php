<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Document</title>



  <style type="text/css">



    #pcolor{
      text-align: center;
    }
    h1{
      font-size: 30;
    }
    h2{
      color: red;
  }
div#center{
  padding: 0px 5px 5px 380px;
  text-align: left;

  font-size: 16px;
  line-height: 200%
}

div#gogogo{

padding: 0px 0px 0px 270px;
font-size: 30px;
font-weight: bold;
font-family: sans-serif;
}
div#gogo{
padding: 0px 0px 0px 380px;
text-align: left;
font-size: 20px

}
div#center2{
  padding: 20px 20px 0px 380px;
  text-align: left;
  font-size: 38px;
  font-weight:bold;

}

div#s_left{
  width: 700px;
  height: 200px;
  background-color: yellow;
  float: left;
}

div#image{
  padding: 0px 0px 0px 380px;
  float: left;
  font-size: 25px;
  font-weight: bold;
}
div#curr{
  padding: 0px 0px 0px 440px;

}
div#currr{
  padding: 0px 0px 0px 380px;

}
div#size{
  font-size: 16.5px;
  line-height: 215%
}
    #nav { width: 100%; float: left; margin: 0 0 1em 0; padding: 0; background-color: #fff;  border-bottom: 1.5px solid #ccc; }
    #nav ul { list-style: none;  width: 600px; margin: 0 auto; padding: 1px 5px 5px 1px; }
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
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

  <!-- 부가적인 테마 -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

  <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


  <!-- Bootstrap core CSS -->
  <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

  <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
  <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
  <script src="../../assets/js/ie-emulation-modes-warning.js"></script>


  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>

  <link href="carousel.css" rel="stylesheet">
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


              </li>
            </ul>
          </div>
        </div>
      </nav>


  </div>



  <div id="pcolor">


      <img src="p12.png" height="225.99px">

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





<div id="center2">

    한국사능력검정 시험이란?
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

</div>

<br>
<br>
<br>
<div id="image">

  <img src="index.png" height="30px" width="10px"> 가장완벽한 커리큘럼
</div>

<br>
<br>
<br>

<br>
<div id="curr">

  <img src="p7.png" />
<div id="gogogo">
<br>

     단 세단계로 한국사 끝낼 수 있습니다

</div>

</div>

<br>
<br>
<br>
<div id="image">
<br><br>
  <img src="index.png" height="30px" width="10px"> 학생들이 가장어려워하는 시험 한국사
  <br>
</div>
<br>
<br>
<br>
<div id="currr">
<br>
<br>
<br>

<img src="p8.png" height="280px" width="280px" align="left" hspace="20">
<div id="size">

  최근 국가직 시험 응시생 대상의 설문조사 결과, 무려 45%의 응시생이 한국사를 가장 어려운 과목으로 꼽았습니다. <br>이는 엄청나게 놀랄일이다. 시기마다 차이는 있지만, 보통 세 가지 필수과목 중에서 가장 어려운 과목을 꼽으라면<br>
  대부분의 수험생이 영어나 한국사를 선택하곤 합니다.2016학년도 수학능력시험부터는 기존의 선택과목이었던 <br>
  국사가 필수과목으로 지정되는 등, 사회적으로 점차 국사 과목의 중요성이 대두되고 있습니다. 공무원 시험에서도  <br>
  예외는 아닙니다. 이미 공무원 한국사 시험 대비를 위한 인터넷 강의는 넘쳐나고 있습니다.
  어떤 강의를 듣더라도 <br>
  한국사 과목은 일단 그 양이 방대하기에, 공무원 시험 다섯 과목 중 영어와 더불어 단연코 가장 <br>
  많은 시간을 투자해야 하는 과목입니다. 대한민국이 반만 년의 역사를 축적해 온 만큼, 한국사 공부는 시험을 위해 <br>
  암기해야 할 것이 가장 많은 과목일 것입니다 3단계로 나누어진 강의 한번이면 됩니다
</div>
<br>
<br>
<br>
<p></p>
<p></p>
<br>
<br>
<br>
<br>


</body>
</html>
