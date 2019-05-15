<?php
$conn=mysqli_connect('localhost','root','136253','store');
$My_id = $_POST['u_id'];
$user_id = $_POST['u_user'];
$content = $_POST['u_content'];
$user_img = $_POST['u_img'];
$num = $_POST['u_num'];
$uset_time = $_POST['user_time'];
$product_index=$_POST['product_index'];

$sql="INSERT into lastchat (lastchat_name,lastchat_content,	lastchat_img,last_user,user_time,product_index) VALUES
 ('{$_POST['u_id']}','{$_POST['u_content']}','{$_POST['u_img']}','{$_POST['u_user']}','{$_POST['user_time']}','{$_POST['product_index']}')";

 $result=mysqli_query($conn,$sql);

if($result){
  echo "saouk_ok";
}else {
  echo "saouK_false";
}




 ?>
