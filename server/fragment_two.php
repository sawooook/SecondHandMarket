<?php

if(isset($_REQUEST['fragment_two_title'])){

  $fragment_two_title=$_REQUEST['fragment_two_title'];
  $decode_fragment_two_title = urldecode($fragment_two_title);

  $fragment_two_money=$_REQUEST['fragment_two_money'];

  $fragment_two_content=$_REQUEST['fragment_two_content'];
  $decode_fragment_two_content = urldecode($fragment_two_content);

  $fragment_two_id=$_REQUEST['fragment_two_id'];
  $fragment_two_check=$_REQUEST['fragment_two_check'];
  $fragment_two_location=$_REQUEST['fragment_two_location'];
  $decode_fragment_two_location = urldecode($fragment_two_location);
  $fragment_two_location_Longtitude=$_REQUEST['fragment_two_location_Longtitude'];
  $fragment_two_location_Latitude=$_REQUEST['fragment_two_location_Latitude'];

}



for($i=0; $i<3; $i++){
  ${"test".$i} = basename( $_FILES['uploadedfile'.$i]['name']);
if(move_uploaded_file($_FILES['uploadedfile'.$i]['tmp_name'], ${"test".$i})) {
echo "uplaoadfinish";
}
}




//
// if(move_uploaded_file($_FILES['uploadedfile']['tmp_name'], $target_path)) {
// echo "$decode_fragment_two_title@$fragment_two_money@$decode_fragment_two_content@$fragment_two_id@
// $fragment_two_check@$decode_fragment_two_location@http://13.58.3.24/$target_path";
//


$conn=mysqli_connect('localhost','root','136253','store');

$sql="INSERT into test (u_id,u_title,u_content,u_price,u_location,u_Longtitude,u_Latitude,u_image,u_check,u_image1,u_image2) VALUES


 ('{$fragment_two_id}',
   '{$decode_fragment_two_title}',
   '{$decode_fragment_two_content}',
   '{$fragment_two_money}',
   '{$decode_fragment_two_location}',
   '{$fragment_two_location_Longtitude}',
   '{$fragment_two_location_Latitude}',
   '{$test0}',
   '$fragment_two_check',
   '{$test1}',
   '{$test2}'
 )";


 $result=mysqli_query($conn,$sql);

echo "hoho";


?>
