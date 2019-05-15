<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
$conn=mysqli_connect('localhost','root','136253','store');

$u_id= $_POST["u_id"];


$sql = "SELECT * FROM wallet WHERE wallet_address ='$u_id'";

$result=mysqli_query($conn,$sql);
$board=mysqli_fetch_array($result);
$count=mysqli_num_rows($result);
// $board=$row[2];
// echo $count;
// echo $board[2];

// var_dump($board['wallet_id']);
// $result['wallet_id'];


if($count==0){

  echo "2";

//아이디가 없을경우

}else{

echo $board[2];
//아이디가 있을경우

}


 ?>
