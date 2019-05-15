<?php
session_start();  ?>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title></title>
  </head>
  <body>
    <?php
if(isset($_SESSION['userid'])){
	session_destroy();
}else{
  echo "로그인인안되어있음";
}
     ?>
<meta charset="utf-8">
<script>alert("로그아웃완료"); location.href="three_km.php"; </script>

  </body>
</html>
