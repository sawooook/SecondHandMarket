<?php

$conn=mysqli_connect('localhost','root','136253','store');
$title = $_POST['u_title'];

$sql = "SELECT * FROM test WHERE u_title = '$title'";
$result=mysqli_query($conn,$sql);
$board=mysqli_fetch_array($result);
echo $board['u_finish'];






//만약 ufinish를 불러와서 1일 경우에 숫자를 2로바꾸고
//ufinsish가 2일경우에는 거래를 완료 했다고함 그이후에 거래 완료를 시킴 그리고 없앰
//거래 완료, 후기남기기,  web



 ?>
