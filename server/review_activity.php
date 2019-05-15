<?php
$conn=mysqli_connect('localhost','root','136253','store');
$id = $_POST['u_id'];
$sql = "SELECT * FROM reviewPage WHERE review_id_to = '$id'";

$result=mysqli_query($conn,$sql);

$array = array();

while ($row = mysqli_fetch_array($result)) {
  array_push($array,
     array('review_id_to'=>$row[1],'review_id_from'=>$row[2],'review_certi'=>$row[3],'review_time'=>$row[4],
     'review_rating'=>$row[5],'review_img'=>$row[6],'review_content'=>$row[7],'image1'=>$row[8]
   ,'image2'=>$row[9],'image3'=>$row[10]));
 }



$json=json_encode(array("result"=>$array),JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
echo $json;

 ?>
