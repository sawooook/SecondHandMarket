<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
session_start();
$conn = mysqli_connect('localhost','root','136253','hihi');

//add 버튼을 눌렀을경우
if(isset($_POST['add'])){
//세션에 집어 넣는다
if(isset($_SESSION['shopping_cart1'])){
// 만약에 세션에 쇼핑카트가 존재한다면
$item_array_id = array_column($_SESSION['shopping_cart1'],"item_id");

if(!in_array($_GET["id"], $item_array_id))
{
   //shopping_cart 세션 배열에 들어있는 배열의 수
   $count =  count($_SESSION["shopping_cart1"]);

   //클릭한 상품의 데이터를 배열에 넣는다.
        $item_array = array(
          'item_id'=>$_GET['id'],
          'item_name'=>$_POST['name'],
          'item_price'=>$_POST['price'],
          'item_day'=>$_POST['day']);

        //shopping_cart 세션 배열에서 그 다음 방부터 차례로 넣는다.
        $_SESSION["shopping_cart1"][$count] = $item_array;


  }else
  {
  //클릭한 상품의 id가 $item_array_id 배열에 존재한다면
  echo '<script>alert("같은 상품이 존재합니다.")</script>';
  echo '<script>window.location="product_cart.php"</script>';

}


}


else{
$item_array = array(
'item_id'=>$_GET['id'],
'item_name'=>$_POST['name'],
'item_price'=>$_POST['price'],
'item_day'=>$_POST['day']);
$_SESSION["shopping_cart1"][0] = $item_array;

}
}

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
          <h3>장바구니</h3>
          <br><br><br><br>

          <table class="table table-bordered">
          <tr>

          <th style="text-align: center;" width="40%">상품</th>
          <th style="text-align: center;"width="10%">기간</th>
          <th style="text-align: center;"width="20%">가격</th>
          <th style="text-align: center;"width="15%">수강상태</th>

          </tr>
    <?php

        if(!empty($_SESSION["shopping_cart1"]))
        { $total=0;

                foreach($_SESSION["shopping_cart1"] as $keys => $values)
              {
              ?>
              <tr>
              <td><?php echo $values["item_name"]; ?></td>
              <td><?php echo $values["item_day"]; ?></td>

              <td><?php echo number_format($values["item_price"]); ?></td>

              <td><a href="product_delete.php?id=<?php echo $values["item_id"]?>"> <span class="text-danger">취소</span> </a></td>

              </tr>

            <?php


            $total = $total + ($values["item_price"]);


           }
         }

;?>
</table>
<br>

<br>
<br>

<table>
<?php
if(!empty($_SESSION['shopping_cart1'])){

  ?>

  <div style="border:0.5px solid #333; background-color:#fff; height: 130px; border-radius:5px;;padding:16px;" align="center">

  <div id ="kkk"><br>

  주문금액: <?php echo number_format($total); ?>원 (상품가합계)+ 0원 (교재비무료) =<?php echo number_format($total); ?>원
  </div>
  </div>
<?php
}
?>

</table>
<br>
<br>
<form action="parent.php" method="post">
  <input type="hidden" name="Amt" value="1000">
  <input type="hidden" name="OrderName" value="<?php echo $values["item_name"]; ?>">
  <input type="submit"style="margin-top:5px; width:100pt;height:30pt;"  name="결제하기" value="결제하기">

</form>
  </body>
</html>
<br><br><br><br><br>
