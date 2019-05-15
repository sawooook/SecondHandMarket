<?php
$conn=mysqli_connect('localhost','root','136253','saouk');
$sql = "SELECT * FROM signup WHERE u_id = '아아'";
$result=mysqli_query($conn,$sql);

$array = array();

while ($row = mysqli_fetch_array($result)) {
  array_push($array,
     array('u_pw'=>$row[2],'u_id'=>$row[1],'photo'=>$row[3]
     ));
 }



$json=json_encode(array("result"=>$array),JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
echo $json;

 ?>
