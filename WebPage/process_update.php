<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
$conn = mysqli_connect('localhost','root','136253','hihi');


  $tmpfile =  $_FILES['file']['tmp_name'];
  $o_name = $_FILES['file']['name'];
  $filename = iconv("UTF-8", "EUC-KR",$_FILES['file']['name']);
  $folder =  $filename;



$sql ="UPDATE board SET
name='{$_POST['update_name']}',
content='{$_POST['update_content']}',
title='{$_POST['update_title']}',
file='$folder'
where id= {$_POST['update_id']}";


$result=mysqli_query($conn,$sql);

if($result === false){
  echo '저장하는 과정에서 문제가 생겼습니다. 관리자에게 문의해주세요';
  error_log(mysqli_error($conn));
} else {
  echo "<script>alert('수정이 완료되었습니다.');</script>";
}


?>
<meta http-equiv="refresh" content="0 url=one.php" />
