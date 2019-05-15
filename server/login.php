<?php
error_reporting(E_ALL);
ini_set('display_errors',1);

$conn=mysqli_connect('localhost','root','136253','store');

$loginUserId = $_POST['u_id'];
$loginPassword= $_POST['u_pw'];

$sql = "SELECT * FROM signup WHERE u_id ='{$_POST['u_id']}'";
$result=mysqli_query($conn,$sql);

if($result){


$board=mysqli_fetch_array($result);

if($board['u_pw']==$loginPassword){

echo "1";


}else{

echo "0";

}
}else {
  echo "0";
}


 ?>
