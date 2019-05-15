<?php

// define("DB_HOST", "localhost");
// define("DB_USER", "root");
// define("DB_PASSWORD", "136253");
// define("DB_NAME", "store");
define("GOOGLE_API_KEY", "AAAAWzYJ2xU:APA91bEpAuI9xug6EENjsDDp8QBzb5fjK_IZYUhrytVEN73QI1dBveRgnOFaZdfG2_bFKBbdjZPxIbRlF5jF4utg81ldMtLyTXf0sN9CXs5XFu1t3HNwjrqcm-bXg3QTZffcHwFmhSkwn1gtxJ7tZarYi9qpf2MIBA");
$myMessage = $_POST['fcm'];
$id =$_POST['fcm_myid'];
$userid=$_POST['fcm_userid'];
$img = $_POST['fcm_img'];
$roomnum=$_POST['fcm_room'];

	function send_notification ($tokens, $message)
	{
		$url = 'https://fcm.googleapis.com/fcm/send';


  	$fields = array(

			 'registration_ids' => $tokens,
			 'data' => $message
			);

		$headers = array(
			'Authorization:key =' . GOOGLE_API_KEY,
			'Content-Type: application/json'
			);

	   $ch = curl_init();
       curl_setopt($ch, CURLOPT_URL, $url);
       curl_setopt($ch, CURLOPT_POST, true);
       curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
       curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
       curl_setopt ($ch, CURLOPT_SSL_VERIFYHOST, 0);
       curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
       curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
       $result = curl_exec($ch);
       if ($result === FALSE) {
           die('Curl failed: ' . curl_error($ch));
       }
       curl_close($ch);
       return $result;
	}




	//데이터베이스에 접속해서 토큰들을 가져와서 FCM에 발신요청

	$conn = mysqli_connect("localhost", "root", "136253", "store");

	$sql = "SELECT * From token where fcm_id ='$userid'";
  //
  // echo $sql
	$result = mysqli_query($conn,$sql);
  var_dump($result) ;
	$tokens = array();

	if(mysqli_num_rows($result) > 0 ){

		while ($row = mysqli_fetch_assoc($result)) {
			$tokens[] = $row["fcm_token"];
      echo $tokens[0];
		}
	}
  //
	mysqli_close($conn);
  //
  //
  //        //폼에서 입력한 메세지를 받음
  //
  //
  //
	$message = array("message" => $myMessage, "id"=>$id, "img"=>$img ,"room"=>$roomnum);
	$message_status = send_notification($tokens, $message);
	echo $message_status;

 ?>
