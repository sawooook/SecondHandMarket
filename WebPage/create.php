<html>
  <head>
fffffffff

<?php
include "buy.php";



$hi=$_POST['so'];

$saouk=$_POST['soso'];

//
// $to = "lna1003@naver.com"; //상대 메일주소
// var_dump($to);
//
// $subject = 'the subject'; //제목
// $message = "$saouk"; //본문
// $headers = 'From: lna1003@example.com' . "\r\n" .
//   'Reply-To: lna1003@example.com' . "\r\n" .
//   'X-Mailer: PHP/' . phpversion(); //헤더설정
// $result=sendmail($to, $subject, $message, $headers); //메일송신


$config=array(
         'host'=>'ssl://smtp.naver.com',
         'smtp_id'=>'lna1003@naver.com',
         'smtp_pw'=>'ghdtkdnr10!',
         'debug'=>0,
         'charset'=>'utf-8',
         'ctype'=>'text/plain'
      );

      $sendmail = new Sendmail($config);

      $to="$hi";
      $from="한방한국사";
      $subject="이메일인증을 해주세요 ";
      $body=$saouk;

      $sendmail->send_mail($to, $from, $subject, $body);





  if(!$result) {
?>

      <script>
              alert('메일전송실패!!! \n 다시 작성하세요');
      </script>

<?php
  } else {
?>

      <script>
              alert('메일전송성공!!!');
      </script>

<?php
  }
?>
<meta http-equiv="refresh" content="0 url=popup.php" />

  </head>
  <body></body>
  </html>
