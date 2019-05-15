<?php

$conn=mysqli_connect('localhost','root','136253','store');
$title = $_POST['u_title'];
// $fisnish_number=$_POST['fisnish_number'];
$sql = "SELECT * FROM test WHERE u_title = '$title'";
$result=mysqli_query($conn,$sql);
$board=mysqli_fetch_array($result);
// $one ='1';
// echo $board['u_finish'];


if($board['u_finish']=='2'){
//만약 2일경우에는 거래가 완료일경우임 그럼 이때는 응갑값을 다르게보내줘야함
echo "finish";


}else{
  if($board['u_finish']=='1'){
  // echo $board['u_finish'];
    $one ='2';
  }

  $sqll="update test set
  u_id='{$board['u_id']}',
  u_title='{$board['u_title']}',
  u_content='{$board['u_content']}',
  u_price='{$board['u_price']}',
  u_location='{$board['u_location']}',
  u_Longtitude='{$board['u_Longtitude']}',
  u_Latitude='{$board['u_Latitude']}',
  u_image='{$board['u_image']}',
  u_check='{$board['u_check']}',
  u_time='{$board['u_time']}',
  u_finish='{$one}'

  where u_title='$title'";

  $result=mysqli_query($conn,$sqll);


  echo $sqll;



}



//만약 ufinish를 불러와서 1일 경우에 숫자를 2로바꾸고
//ufinsish가 2일경우에는 거래를 완료 했다고함 그이후에 거래 완료를 시킴 그리고 없앰
//거래 완료, 후기남기기,  web



 ?>
