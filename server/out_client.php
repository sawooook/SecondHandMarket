<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
$conn=mysqli_connect('localhost','root','136253','store');

$id =$_POST['u_id']; //내아이디를 넣음
$user_time=$_POST['u_time'];
$user_msg=$_POST['u_msg'];


$sql="UPDATE lastchat
SET
lastchat_read='0',
count_msg='$user_msg'
where
lastchat_name='$id' AND user_time='$user_time'";


$result=mysqli_query($conn,$sql);




 ?>
