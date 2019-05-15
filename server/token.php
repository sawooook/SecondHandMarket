<?php

if(isset($_POST["Token"])){

  $token = $_POST["Token"];
}//데이터베이스에 접속해서 토큰저장

  $token = "123";
include_once 'config.php';
$conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME);
$query = "INSERT INTO token (fcm_token) Values ('$token') ON DUPLICATE KEY UPDATE fcm_token = '$token'; ";
mysqli_query($conn, $query);


?>
