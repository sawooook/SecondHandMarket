<?php
error_reporting(E_ALL);
ini_set('display_errors',1);

$conn=mysqli_connect('localhost','root','136253','store');


$sql = "SELECT * FROM signup WHERE nickname ='{$_POST['nickname']}'";
$result=mysqli_query($conn,$sql);
$count=mysqli_num_rows($result);

if($result){
if($count==0){
  //유저아이다가 없을경우


echo "success";


}else{

  //유저아이다가 없을경우
echo "false";

}
}else{
  echo "0";
}

 ?>
