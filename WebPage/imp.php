<?php
error_reporting(E_ALL);
ini_set('display_errors',1);
session_start();
$hi=$_GET['id'];
$conn = mysqli_connect('localhost','root','136253','hihi');


  $sql="INSERT into cartprice (name,list,date) VALUES
   ('{$_SESSION['userid']}','{$_GET['id']}',NOW())";

  $result = mysqli_query($conn,$sql)or die(mysqli_error());



 ?>
<script type="text/javascript">
              location.href="mypage.php"
</script>
