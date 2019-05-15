<?php



$conn=mysqli_connect('localhost','root','136253','store');

$id=$_POST['u_id'];

$sql="UPDATE id_plz SET unity_id='{$_POST['u_id']}' WHERE '1'";
$row = mysqli_query($conn,$sql);



 ?>
