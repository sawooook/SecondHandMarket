




<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
session_start();
$conn = mysqli_connect('localhost','root','136253','hihi');
$sql = "SELECT * FROM cartprice";
$result = mysqli_query($conn,$sql);




?>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>한방한국사 장바구니</title>  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <style type="text/css">




    #pcolor{
      text-align: center;
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

  padding: 0px 0px 0px 210px;
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
  font-size: 20px;
  font-weight: bold;
  }
  div#curr{
  padding: 0px 0px 0px 500px;

  }
  div#currr{
  padding: 0px 0px 0px 380px;

  }
  div#kkk{

    font-size: 22px;
    text-align: center;
  }

  li{

  font-size: 20px;

  list-style: none;
  }
  li a{

  color: black;
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
    div#pp {
      border-top-width : 3px;
    }
h2{
padding: 0px 0px 0px 580px;

}

.line1{border-top: 3px solid black;
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

    <li><a href="product_cart.php">장바구니</a></li>
                                <li><a href="logout.php?board">로그아웃</a></li>


                                <li><a href="signup.php?signup"> 회원가입</a></li>


              </ul>
            </div>
          </div>
        </nav>


    </div>



  <div id="pcolor">

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

          <div style="clear:both"></div>

          <br>
<div class="container" style="width:900px;">
          <h3>수강신청내역</h3>
          <br><br><br><br>



          <table class="table table-bordered">
          <tr>

          <th style="text-align: center;" width="40%">상품</th>

          <th style="text-align: center;"width="20%">신청날짜</th>
          <th style="text-align: center;"width="15%">결제상태</th>

          </tr>
          <?php
                    while($row = mysqli_fetch_array($result)){

                      ?>


              <tr>
              <td><?php echo $row['list']?></td>
              <td><?php echo $row['date']?></td>
              <td><a href ="cart.php?id=<?=$row['ind']?>"  onclick="return confirm('정말 삭제 하시겠습니까?')">취소</a></td>



              </tr>




<?php }?>
</table>
<br>

<br>
<br>


<br>
<br>

  </body>
</html>
<br><br><br><br><br>
