<?php
$conn=mysqli_connect('localhost','root','136253','store');
$id = $_POST['u_id'];
$sql = "SELECT * FROM lastchat
 WHERE lastchat_name = '$id'
 Order by timestamo DESC";

$result=mysqli_query($conn,$sql);

$array = array();

while ($row = mysqli_fetch_array($result)) {
  array_push($array,
     array('product_index'=>$row[6],'user_time'=>$row[5],'lastchat_name'=>$row[1],'lastchat_content'=>$row[2],'lastchat_img'=>$row[3],'last_user'=>$row[4]));
 }



$json=json_encode(array("result"=>$array),JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
echo $json;

 ?>
