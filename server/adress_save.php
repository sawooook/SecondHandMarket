<?php

error_reporting(E_ALL);
ini_set('display_errors',1);
$conn=mysqli_connect('localhost','root','136253','store');


$address_id= $_POST["address_id"];
$u_id= $_POST["u_id"];


$sql = "SELECT * FROM wallet WHERE wallet_id ='$address_id'";
$result=mysqli_query($conn,$sql);
$count=mysqli_num_rows($result);




if($count==0){
  $sql="INSERT into wallet (wallet_address,wallet_id) VALUES
   ('{$_POST['u_id']}','{$_POST['address_id']}')";
    $result=mysqli_query($conn,$sql);
  echo "yes save";


}else{


echo "nono";


}


 ?>
