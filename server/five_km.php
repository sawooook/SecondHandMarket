<?php
session_start();
error_reporting(E_ALL);
ini_set('display_errors',1);


$id=$_POST['loginID'];
$password=$_POST['loginPASSWORD'];


if($id=='admin'&&$password=='136253'){

  $_SESSION['userid']='admin';
  $_SESSION['userpw']='136253';


  echo "<script>alert('로그인완료'); location.href='SYtoken.php';</script>";
}else{
  echo "<script>alert('로그인실패'); location.href='three_km.php';</script>";
}

 ?>
