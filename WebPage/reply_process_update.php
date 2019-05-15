<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
$conn = mysqli_connect('localhost','root','136253','hihi');
session_start();

$sql ="UPDATE reply SET
name='{$_SESSION['userid']}',
content='{$_POST['content']}'
where ind= {$_GET['id']}";


$result=mysqli_query($conn,$sql);

if($result === false){
  echo '저장하는 과정에서 문제가 생겼습니다. 관리자에게 문의해주세요';
  error_log(mysqli_error($conn));
} else {
  echo "<script>alert('수정이 완료되었습니다.');</script>";
}


?>
<meta http-equiv="refresh" content="0 url=one.php" />
