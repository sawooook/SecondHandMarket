<?php
$conn=mysqli_connect('localhost','root','136253','store');
$id = $_POST['u_id'];
$sql = "SELECT * FROM test Order by u_time desc";

$result=mysqli_query($conn,$sql);

$array = array();

while ($row = mysqli_fetch_array($result)) {
  array_push($array,
     array('u_id'=>$row[1],'u_title'=>$row[2]
     ,'u_content'=>$row[3],'u_price'=>$row[4]
     ,'u_Longtitude'=>$row[6],'u_Latitude'=>$row[7]
     ,'u_location'=>$row[5],'u_image'=>$row[8]
     ,'u_check'=>$row[9],'u_time'=>$row[10]
     ));
 }



$json=json_encode(array("result"=>$array),JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
echo $json;

 ?>
