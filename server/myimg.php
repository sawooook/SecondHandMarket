<?php
error_reporting(E_ALL);
ini_set('display_errors',1);

$conn=mysqli_connect('localhost','root','136253','store');



$sql = "SELECT * FROM signup WHERE u_id ='{$_POST['u_id']}'";
$result=mysqli_query($conn,$sql);

$board=mysqli_fetch_array($result);

echo $board['photo'];


 ?>
