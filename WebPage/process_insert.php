<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
$conn = mysqli_connect(
  'localhost',
  'root',
  '136253',
  'hihi');

  $tmpfile =  $_FILES['file']['tmp_name'];

  $o_name = $_FILES['file']['name'];
  $filename = iconv("UTF-8", "EUC-KR",$_FILES['file']['name']);
  $folder =  $filename;




  // for($i=0; $i<14; $i++){
    $sql="INSERT into board (title,content,name,hit,date,file) VALUES
     ('{$_POST['title']}','{$_POST['content']}','{$_POST['name']}',0,NOW(),'$folder')";

    $result = mysqli_query($conn,$sql)or die(mysqli_error());
  // }
echo "<script>alert('후기작성이 완료되었습니다.');</script>";
?>
<meta http-equiv="refresh" content="0 url=one.php" />
