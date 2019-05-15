<?php
$conn=mysqli_connect('localhost','root','136253','store');


$sql = "SELECT * FROM marker";

$result=mysqli_query($conn,$sql);

$array = array();

while ($row = mysqli_fetch_array($result)) {
  array_push($array,
     array('marker_location'=>$row[1],'marker_Latitude'=>$row[2],'marker_Longtitde'=>$row[3],'marker_title'=>$row[4]));
 }



$json=json_encode(array("result"=>$array),JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
echo $json;

 ?>
