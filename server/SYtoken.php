




<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
session_start();
$conn=mysqli_connect('localhost','root','136253','store');
$sql = "SELECT * FROM SYtoken ORDER BY sy_index DESC";
$result = mysqli_query($conn,$sql);




?>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>SY토큰 관리자 페이지</title>  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
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

  <!-- 遺媛?곸씤 ?뚮쭏 -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

  <!-- ?⑹퀜吏怨?理쒖냼?붾맂 理쒖떊 ?먮컮?ㅽ겕由쏀듃 -->
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

    <li><a href="product_cart.php"></a></li>
                        
                                    <li><a href="hh.php">로그아웃</a></li>




              </ul>
            </div>
          </div>
        </nav>


    </div>



  <div id="pcolor">



            <br>

            <nav class="navbar navbar-default">

                <div class="navbar-header">


                </div>
                <div id="navbar" class="navbar-collapse collapse">
                  <ul class="nav nav-justified">
                    <li><a href="three_km.php">HOME</a></li>
                    <li><a href="SYtoken.php">토큰지급</a></li>

                  </ul>

                </div>

            </nav>

          <div style="clear:both"></div>

          <br>
<div class="container" style="width:900px;">
          <h3>SYtoken 관리자페이지</h3>
          <br><br><br><br>



          <table class="table table-bordered">
          <tr>

          <th style="text-align: center;" width="15%">INDEX</th>

          <th style="text-align: center;"width="15%">ID</th>
          <th style="text-align: center;"width="30%">지갑주소</th>
          <th style="text-align: center;"width="15%">종류</th>
            <th style="text-align: center;"width="15%">수량</th>
            <th style="text-align: center;"width="10%">지급여부</th>

          </tr>
          <?php
                    while($row = mysqli_fetch_array($result)){

                      ?>


              <tr>
              <td><?php echo $row['sy_index']?></td>
              <td><?php echo $row['sy_id']?></td>
              <td><?php echo $row['sy_address']?></td>
              <td><?php echo $row['sy_type']?></td>
              <td><?php echo $row['sy_amount']?></td>



              <?php
              if($row['sy_confirm']=="확인"){

                ?>


                <td><a href ="gogo.php?id=<?=$row['sy_index']?>" >지급하기</a></td>
                <?php
              }else{
              ?>
              <td><?php echo "지급완료"?></td>

              <?php
              }
 ?>

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
