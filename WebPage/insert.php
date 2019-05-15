<?php
error_reporting(E_ALL);
ini_set('display_errors',1);


$conn = mysqli_connect('localhost','root','136253','hihi');
$sql= "SELECT * FROM cart where id ={$_GET['id']}";

$result=mysqli_query($conn,$sql);
$row = mysqli_fetch_array($result);

?>


<!DOCTYPE html>
<html lang="en" dir="ltr">
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
    div#o{
            padding: 0px 0px 0px 60px;
      }
      li{

      font-size: 20px;

      list-style: none;
      }
      li a{

      color: black;
      }
    div#image{
      padding: 0px 0px 0px 200px;

      font-size: 20px;
      font-weight: bold;
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

            <meta charset="utf-8">
            <title>한방한국사 강의 소개</title>
      </head>
    <!-- NAVBAR
    ================================================== -->
      <body>
        <script type="text/javascript" src="js/sobootstrap.js"></script>


        <div class="navbar-wrapper">



            <nav class="navbar-inverse navbar-static-top">
              <div class="container">

                <div id="navv" class="navbar-collapse collapse">
                  <ul class="navbar-nav">
    <?php


    if(isset($_SESSION['userid'])){

    ?>              <li><a href="product_cart.php">장바구니</a></li>
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




    <div class="container">
<h1><?php echo $row["name"]; ?><br></h1>
<br><br><br>
<div id="image">
    <img src="<?php echo $row["img"]; ?>" class="img-responsive" /> </br>
    <br><br>
</div>
    <div id="o">
<div style="border:1px solid #333; height: 300px; width: 1000px;background-color:#ffffff; border-radius:5px; margin: 0px 10px 40px 0px ;padding:40px 0px 300px 20px;" align="center">
<h1><?php echo $row["name"]; ?><br></h1>
<h2>가격:<?php echo $row["price"]; ?>원  /  기간:<?php echo $row["day"]; ?>일  <br></h2>
<br>
<h3><?php echo $row["description"];?></h3>
<br>
    <form action="product_cart.php?id=<?=$_GET['id'];?>" method="post">

      <input type="hidden" name="img" value="<?php echo $row["img"] ?>" />
      <input type="hidden" name="day" value="<?php echo $row["day"] ?>" />
      <input type="hidden" name="name" value="<?php echo $row["name"] ?>" />
      <input type="hidden" name="price" value="<?php echo $row["price"] ?>" />
      <input type="submit" name="add" value="신청하기">

    </form>
</div>

</div>

<img src="p10.png" class="img-responsive">
<br><br><br><br><br>
  </body>
</html>
