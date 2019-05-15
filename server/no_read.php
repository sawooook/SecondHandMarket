<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
$conn=mysqli_connect('localhost','root','136253','store');

$id =$_POST['u_id']; //내아이디를 넣음
$user_time=$_POST['u_time'];




$sql = "SELECT * FROM lastchat WHere last_user='$id'AND user_time='$user_time'";
$result=mysqli_query($conn,$sql);

$board=mysqli_fetch_array($result);

echo $board['lastchat_read'];

//lastuser는 상대방아이디 아씨발
//lastchat 내아이디


 ?>
