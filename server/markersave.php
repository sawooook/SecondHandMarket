
<?php
$conn=mysqli_connect('localhost','root','136253','store');
$location=$_POST['location'];
$location_Latitude = $_POST['location_Latitude'];
$location_Longtitude = $_POST['location_Longtitude'];
$location_title=$_POST['marker_title'];
$decode_title=urldecode($location_title);
$decode_location=urldecode($location);



  $sql="INSERT into marker (marker_location,marker_Latitude,marker_Longtitde,marker_title) VALUES
   ('{$decode_location}','{$_POST['location_Latitude']}','{$_POST['location_Longtitude']}','{$decode_title}')";

$row = mysqli_query($conn,$sql);

if($row){
  echo "saouk";
}else {
  echo "fail";
}

 ?>
