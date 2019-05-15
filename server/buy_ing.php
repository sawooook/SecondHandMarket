<?php
$conn=mysqli_connect('localhost','root','136253','store');
$id = $_POST['u_id'];
$sql = "SELECT * FROM test WHERE u_id = '$id'";

$result=mysqli_query($conn,$sql);

$array = array();

while ($row = mysqli_fetch_array($result)) {
  array_push($array,
     array('u_title'=>$row[2],'u_image'=>$row[8],'u_time'=>$row[10]));
 }



$json=json_encode(array("result"=>$array),JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
echo $json;

 ?>
