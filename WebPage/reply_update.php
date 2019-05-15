<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
$conn = mysqli_connect('localhost','root','136253','hihi');

$sql = "SELECT *fROM reply WHERE ind ={$_GET['id']}";

$result=mysqli_query($conn,$sql);

$row=mysqli_fetch_array($result);

 ?>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <style media="screen">

    </style>
    <meta charset="utf-8">
    <title></title>
  </head>
  <body>


    <form action="reply_process_update.php?id=<?=$row['ind']?>" method="post">

    <textarea name="content" class="dap_edit_t" required="required"></textarea>
    <input type="submit" value="수정하기" class="re_mo_bt">
    </form>


  </body>
</html>
