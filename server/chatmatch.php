
<?php
$conn=mysqli_connect('localhost','root','136253','store');

$id =$_POST['chat_id'];
$content=$_POST['chat_content'];
$img=$_POST['chat_img'];


$sql="INSERT into lastchat (lastchat_name,lastchat_content,	lastchat_img) VALUES
 ('{$_POST['chat_id']}','{$_POST['chat_content']}','{$_POST['chat_img']}')";


 $result=mysqli_query($conn,$sql);

if($result){
  echo "saouk_ok";
}else {
  echo "saouK_false";
}





 ?>
