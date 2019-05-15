
<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
$conn=mysqli_connect('localhost','root','136253','lna1003');

$sql = "SELECT * FROM oo WHERE u_id = '호욱이'";
$result=mysqli_query($conn,$sql);
$data = array();

while($row = mysqli_fetch_array($result)){
  array_push($data,array(
    'u_id' => $row[0],
    'u_pw' => $row[1] ,
    'photo' => $row[3])
  );
}
$output = json_encode ( array ("result" => $data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);



echo  $output ;





 ?>
