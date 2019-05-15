<?php
error_reporting(E_ALL);
ini_set('display_errors',1);

$conn =mysqli_connect("localhost","root","136253","oo");


if (mysqli_connect_errno($conn))

{

 echo "Failed to connect to MySQL: " . mysqli_connect_error();

}else {




  $sql="INSERT into oo (u_id,u_pw) VALUES
   ('{$_POST['username']}','{$_POST['password']}')";


   $result=mysqli_query($conn,$sql);
}  if($result){

    echo 'success';

  }

  else{

    echo 'failure';

  }
  mysqli_close($conn);

 ?>
