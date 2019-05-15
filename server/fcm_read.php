<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
$conn=mysqli_connect('localhost','root','136253','store');

$id =$_POST['u_id']; //내아이디를 넣음
$user_time=$_POST['u_time'];//방값을 가져옴


//방번호랑 구매자 아이디를 보냄
//그리고 행을 찾음 그행의 아이디가 숫자가 0일경우에 메세지를 보//



$sql = "SELECT * FROM lastchat WHere last_user='$id'AND user_time='$user_time'";
$result=mysqli_query($conn,$sql);

$board=mysqli_fetch_array($result);

echo $board['lastchat_read'];




 ?>
