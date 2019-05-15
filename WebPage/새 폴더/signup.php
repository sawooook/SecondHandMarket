<?php
$conn=mysqli_connect('localhost','root','136253','saouk');

$imageurl = $_POST['image'];
$filename = $imageurl;
$data= file_get_contents($imageurl);
var_dump($filename);
$really=file_put_contents ($filename,$data);

  $sql="INSERT into signup (u_id,u_pw,photo,nickname) VALUES
   ('{$_POST['username']}','{$_POST['password']}', '$filename','{$_POST['nickname']}')";

$row = mysqli_query($conn,$sql);


 ?>
