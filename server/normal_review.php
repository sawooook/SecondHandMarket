<?php

$conn=mysqli_connect('localhost','root','136253','store');

$id=$_POST['review_id_from'];




$sql2 = "SELECT * FROM signup where u_id ='$id'";
$result2 = mysqli_query($conn,$sql2);
$board2 = mysqli_fetch_array($result2);
$board2['photo'];


for($i=0; $i<3; $i++){
  ${"test".$i} = basename( $_FILES['uploadedfile'.$i]['name']);
if(move_uploaded_file($_FILES['uploadedfile'.$i]['tmp_name'], ${"test".$i})) {
echo 'hh';

}
}

if(isset($_REQUEST['review_id_to'])){
//내아이디를 받음
  $review_id_to=$_REQUEST['review_id_to'];
//상대방아이디를 받음
  $review_id_from=$_REQUEST['review_id_from'];
//url 해독후 넣음
  $review_certi=$_REQUEST['review_certi'];
  $decode_review_certi = urldecode($review_certi);
  //별점을 받음
  $review_rating=$_REQUEST['review_rating'];
//이것또한 해독후 넣음
  $review_content=$_REQUEST['review_content'];
  $decode_review_content = urldecode($review_content);

echo $decode_review_content;

}



$sql="INSERT into reviewPage (review_id_to,review_id_from,review_certi,review_rating,review_img,review_content,image1,image2,image3) VALUES
 ('$review_id_to','$review_id_from','$decode_review_certi','$review_rating','{$board2['photo']}'
 ,'$decode_review_content','{$test0}','{$test1}','{$test2}')";



$result = mysqli_query($conn,$sql);



 ?>
