<?php



$conn=mysqli_connect('localhost','root','136253','store');

$score = $_POST['score_number'];
$id = $_POST['id'];

$sql="INSERT into rank (rank_id,rank_score) VALUES
 ('{$_POST['id']}','{$_POST['score_number']}')";

$row = mysqli_query($conn,$sql);


 ?>
