<?php




if(isset($_REQUEST['name'])){

  $mypage_shared_id=$_REQUEST['shared_id'];
  $mypage_shared_pw=$_REQUEST['shared_pw'];
  $mypage_shared_nickname=$_REQUEST['shared_nickname'];
  $mypage_id=$_REQUEST['name'];
  $mypage_nickname=$_REQUEST['nickname'];



}
$conn=mysqli_connect('localhost','root','136253','store');



$target_path = basename( $_FILES['uploadedfile']['name']);

if(move_uploaded_file($_FILES['uploadedfile']['tmp_name'], $target_path)) {
echo "$mypage_id@$mypage_nickname@http://52.14.144.55/$target_path";

$sql ="UPDATE signup SET

u_id='{$_REQUEST['shared_id']}',
u_pw='{$_POST['shared_pw']}',
photo='http://52.14.144.55/{$target_path}',
nickname='{$_REQUEST['nickname']}'
where u_id= '{$_REQUEST['shared_id']}'";

$row = mysqli_query($conn,$sql);

}else{
  echo "실패둥이";
}



?>
