<?php
$conn = mysqli_connect('localhost','root','136253','hihi');

$sql = "DELETE fROM reply WHERE ind ={$_GET['id']}";


$result=mysqli_query($conn,$sql);



echo "<script>alert('댓글이 삭제되었습니다.');</script>"
?>
<meta http-equiv="refresh" content="0 url=one.php" />
