<?php
date_default_timezone_set('Asia/Seoul');
$conn=mysqli_connect('localhost','root','136253','store');

$ididid=$_POST['u_id'];
$sql = "SELECT * FROM time WHERE time_id ='$ididid'";

// echo $sql;
$result=mysqli_query($conn,$sql);
$board=mysqli_fetch_array($result);
$count=mysqli_num_rows($result);
//일단 아이디가 있는지 없는지 확인을함

$u_id=$_POST['u_id'];
$u_wallet=$board['wallet_id']; //내아이디
$u_ad=$_POST['u_ad']; //광고
$u_eth=$_POST['u_eth']; //받을이더
$sy_confirm=$_POST['sy_confirm']; //확인

if($count==1){
  //만약 해당 아이디가 존재할경우

$time=strtotime($board['time_time']);
//db에 저장된 시간을

$timestamp = strtotime("Now");
//그리고 현재시간을 불러옴
// echo $from_time;

$time_minus=round(abs($timestamp - $time) / 60,2);

// echo $time_minus;
//현재시간에서 db저장시간을 계산함

if($time_minus>"0.5"){
  //만약 조회수 클릭 시간이 60분이 지났을경우 db에서 데이터를 삭제해줌
  //그리고 새롭게 데이터를 추가해줌



    $sql="DELETE FROM time WHERE time_id='$ididid'";
    $result=mysqli_query($conn,$sql);


$sql = "SELECT * FROM wallet WHERE wallet_address ='$ididid'";
// echo $sql;
$result=mysqli_query($conn,$sql);
$board=mysqli_fetch_array($result);

$u_id=$_POST['u_id'];
$u_wallet=$board['wallet_id']; //내아이디
$u_ad=$_POST['u_ad']; //광고
$u_eth=$_POST['u_eth']; //받을이더
$sy_confirm=$_POST['sy_confirm']; //확인


$sql3="INSERT into SYtoken (sy_id,sy_address,sy_type,sy_amount,sy_confirm) VALUES
('$u_id','$u_wallet','$u_ad','$u_eth','$sy_confirm')";
    $result=mysqli_query($conn,$sql3);




echo "time";

}else if($time_minus<"0.5"){

echo "notime";
//이때는 이미 아

}
//   // echo "이미 광고를 시청하셨습니다";
}else {
//만약 아이디가 없을경우 아이디르 추가해야함 즉이말은 db에추가함
//그럼 1일 경우 0일경우 잖아

//아이디가 없을경우에는 time과 SYtoken에 아이디를 생성해줘야함 근데 wallet_address 조회했는데 아이다가 없다
//그러면 지갑을 만들어 달라는 호출메세지를 보내야해 그다음 지갑을 만들었을경우에 time을 같이 생성해야해



//해당아이디가 없을경우 들어와짐여기는
    $sql = "SELECT * FROM wallet WHERE wallet_address ='$ididid'";
    // echo $sql;
    $result=mysqli_query($conn,$sql);
    $board=mysqli_fetch_array($result);


    $count=mysqli_num_rows($result);

    if($count==0){
      // 해당 아이디가 없으면서 지갑이 없을경우
      echo "wallet";


    }else{

//해당아이디가 없으면서 지갑이 있을경우
$u_id=$_POST['u_id'];
$u_wallet=$board['wallet_id']; //내아이디
$u_ad=$_POST['u_ad']; //광고
$u_eth=$_POST['u_eth']; //받을이더
$sy_confirm=$_POST['sy_confirm']; //확인

//여기는 지갑이있을경우야 여기서 time있음 SYtoen
$u_id3=$_POST['u_id'];

$nowtime=date("Y-m-d H:i:s");

$sql="INSERT into time (time_id,time_time) VALUES('$u_id3','$nowtime')";
$result=mysqli_query($conn,$sql);


      $sql3="INSERT into SYtoken (sy_id,sy_address,sy_type,sy_amount,sy_confirm) VALUES
      ('$u_id','$u_wallet','$u_ad','$u_eth','$sy_confirm')";
          $result=mysqli_query($conn,$sql3);

          echo "insert";
          // echo $sql;

    }





}

//
//
//
//
// // var_dump($count);
// // echo $board['time_time'];
// //해당아이디가 있는지 없는지 확인
//
//
// // echo $timestamp;
// //현재시간을 구함
// // echo "현재 일시 : ".date("Y-m-d H:i:s", $timestamp)."<br/>";
//
//
// // $to_time = strtotime("2008-12-13 10:42:00");
// // $from_time = strtotime("2008-12-13 10:21:00");
// // echo round(abs($to_time - $from_time) / 60,2). " minute";
//
//
//
// // $to_time = strtotime("2008-12-13 10:42:00");
// // $ggg =strtotime("2018-09-11 04:32:05");
// // echo round(abs($timestamp - $from_time) / 60,2). " minute";
//
//
//
// // $ad_id ="saouk2";
// //
// //
// // setcookie($ad_id, "Alex Porter", time() + 10);
// //
// //
// //
// // if (isset($_COOKIE[$ad_id])) {
// //
// // echo "kk";
// //
// // } else {
// //   // //
// //   $sql="INSERT into SYtoken (sy_id,sy_address,sy_type,sy_amount,sy_confirm) VALUES
// //    ('sss','ssss','sss','sd','ccc')";
// //    $result=mysqli_query($conn,$sql);
// //    echo $ad_id;
// //
// //
// // }
//


 ?>
