<?php



$conn=mysqli_connect('localhost','root','136253','store');

$id = $_POST['id'];
//내아이디
$room = $_POST['room'];
//webrtc방번호
$fromid =$_POST['u_id']
//상대방아이디

$sql="UPDATE webrtc
SET
webrtc_id='$id',
webrtc_room='$room',
webrtc_from_id='$fromid'
where '1'";


$row = mysqli_query($conn,$sql);


 ?>
