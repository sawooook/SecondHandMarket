<?php






$conn=mysqli_connect('localhost','root','136253','store');
$title = "saouk";
// $fisnish_number=$_POST['fisnish_number'];

//여기서 라스트유저는 나의아이디
$sql = "SELECT * FROM lastchat WHERE last_user = '$title'";
//나의 아이디를 통해서 원하는 행을 불러옴
//그후에 넣고 돌림

$result=mysqli_query($conn,$sql);
$board=mysqli_fetch_array($result);
$one ='1';


$sqll="update lastchat set
lastchat_name='{$board['lastchat_name']}',
lastchat_content='{$board['lastchat_content']}',
lastchat_img='{$board['lastchat_img']}',
last_user='{$board['last_user']}',
user_time='{$board['user_time']}',
product_index='{$board['product_index']}',
lastchat_finish='{$one}'


where last_user='$title'";

$result=mysqli_query($conn,$sqll);


echo $sqll;



//만약 ufinish를 불러와서 1일 경우에 숫자를 2로바꾸고
//ufinsish가 2일경우에는 거래를 완료 했다고함 그이후에 거래 완료를 시킴 그리고 없앰
//거래 완료, 후기남기기,  web











// $conn=mysqli_connect('localhost','root','136253','store');
// $title = $_POST['u_title'];
// $fisnish_number=$_POST['fisnish_number'];
// $sql = "SELECT * FROM test WHERE u_title = '$title'";
// $result=mysqli_query($conn,$sql);
// $board=mysqli_fetch_array($result);
// $one ='1';
//
//
// $sqll="update test set
// u_id='{$board['u_id']}',
// u_title='{$board['u_title']}',
// u_content='{$board['u_content']}',
// u_price='{$board['u_price']}',
// u_location='{$board['u_location']}',
// u_Longtitude='{$board['u_Longtitude']}',
// u_Latitude='{$board['u_Latitude']}',
// u_image='{$board['u_image']}',
// u_check='{$board['u_check']}',
// u_time='{$board['u_time']}',
// u_finish='{$one}'
//
// where u_title='$title'";
//
// $result=mysqli_query($conn,$sqll);
//
//
// echo $sqll;



//만약 ufinish를 불러와서 1일 경우에 숫자를 2로바꾸고
//ufinsish가 2일경우에는 거래를 완료 했다고함 그이후에 거래 완료를 시킴 그리고 없앰
//거래 완료, 후기남기기,  web



 ?>
