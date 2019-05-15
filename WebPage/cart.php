<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
$conn = mysqli_connect('localhost','root','136253','hihi');
$conn = mysqli_connect('localhost','root','136253','hihi');

$sql = "DELETE fROM cartprice WHERE ind ={$_GET['id']}";

$result=mysqli_query($conn,$sql);



echo "<script>alert('수강신청이 취소되었습니다.');</script>"
?>
<meta http-equiv="refresh" content="0 url=mypage.php" />
