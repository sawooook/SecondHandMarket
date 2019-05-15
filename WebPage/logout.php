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
  echo "오류발생";
}
     ?>
<meta charset="utf-8">
<script>alert("로그아웃되었습니다."); location.href="mainpage.php"; </script>

  </body>
</html>
