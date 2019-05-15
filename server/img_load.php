<?php
$conn=mysqli_connect('localhost','root','136253','store');
$id = $_POST['u_fragment_two_u_id'];
$sql = "SELECT * FROM signup WHERE u_id = '$id'";

$result=mysqli_query($conn,$sql);
$board=mysqli_fetch_array($result);

echo $board['photo'];




 ?>
