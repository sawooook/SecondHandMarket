<?php
session_start();
error_reporting(E_ALL);
ini_set('display_errors',1);

$conn = mysqli_connect('localhost','root','136253','hihi');
$id=$_POST['loginID'];
$password=$_POST['loginPASSWORD'];

$sql ="SELECT * FROM signup where id ='$id'";

$result =mysqli_query($conn,$sql);

$board = mysqli_fetch_array($result);

if($id==$board['id']&&$password==$board['passwd']){

  $_SESSION['userid']=$board["id"];
  $_SESSION['userpw']=$board["passwd"];
  $_SESSION['nickname']=$board["nickname"];

  echo "<script>alert('로그인되었습니다'); location.href='mainpage.php';</script>";
}else{
  echo "<script>alert('아이디혹은 비밀번호를 확인하세요'); location.href='login.php';</script>";
}

 ?>
