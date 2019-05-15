<?php



$conn=mysqli_connect('localhost','root','136253','store');

$sql= "SELECT * FROM id_plz WHERE 1";
$row = mysqli_query($conn,$sql);
// var_dump($row);
// print_r($row);
// echo $row;
$board=mysqli_fetch_array($row);
echo $board['unity_id'];


 ?>
