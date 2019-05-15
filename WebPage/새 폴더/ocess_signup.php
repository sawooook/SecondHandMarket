<?php
$conn=mysqli_connect('localhost','root','136253','hihi');
error_reporting(E_ALL);
ini_set('display_errors',1);
	$userid['id'] = $_POST['id'];
	$userpw['passwd'] =$_POST['passwd'];
	$username['nickname'] = $_POST['nickname'];
	$email['email'] = $_POST['email'];
	$address['address'] = $_POST['sample4_postcode'];


$sql = "insert into signup (id,passwd,nickname,address,email) values('{$userid['id']}','{$userpw['passwd']}','{$username['nickname']}','{$address['address']}','{$email['email']}')";

$result=mysqli_query($conn,$sql);

?>

<meta charset="utf-8" />
<script type="text/javascript">alert('회원가입이 완료되었습니다.');</script>
<meta http-equiv="refresh" content="0 url=login.php">
