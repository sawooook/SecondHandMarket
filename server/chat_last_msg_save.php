
<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
$conn=mysqli_connect('localhost','root','136253','store');

$id =$_POST['u_id']; //상대방아이디
$content=$_POST['u_content'];
$user_time=$_POST['u_time'];
$timestamp=$_POST['timestamo'];

$sql2 = "SELECT * FROM lastchat WHere last_user='$id'AND user_time='$user_time'";

//여기서 선택한게 상개방아이디랑 방값을 넣고 해당 열을 선택함
$result2=mysqli_query($conn,$sql2);
$board2=mysqli_fetch_array($result2);


// var_dump($result2);
//lastchatname이 바로 채팅중인 상대방아이디 그러니까 이아이디를 가져




$sql="UPDATE lastchat
SET
lastchat_name='{$board2['lastchat_name']}',
lastchat_content='$content',
lastchat_img='{$board2['lastchat_img']}',
last_user='{$board2['last_user']}',
user_time='{$board2['user_time']}',
product_index='{$board2['product_index']}',
timestamo='$timestamp'
where
last_user='$id'AND user_time='$user_time'";




 $result=mysqli_query($conn,$sql);

 var_dump($result);







 ?>
