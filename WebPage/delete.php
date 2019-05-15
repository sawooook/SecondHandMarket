<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
$conn = mysqli_connect(
  'localhost',
  'root',
  '136253',
  'hihi');

  $sql ="DELETE from board WHERE id={$_GET['id']}";
  $result = mysqli_query($conn,$sql);
  echo "<script> alert('삭제완료');</script>";

 ?>
 <meta http-equiv="refresh" content="0 url=one.php" />
