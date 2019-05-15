<?php
$conn = mysqli_connect(
  'localhost',
  'root',
  '136253',
  'hh');

settype($_POST['id'], 'integer');
$filtered = array(
  'id'=>mysqli_real_escape_string($conn, $_POST['id'])
);

$sql = "
 DELETE
   FROM topic
    WHERE id = {$filtered['id']}
";
$result = mysqli_query($conn, $sql);
if($result === false){
  echo '저장하는 과정에서 문제가 생겼습니다. 관리자에게 문의해주세요';
  error_log(mysqli_error($conn));
} else {
  echo "<script>alert('삭제가 완료되었습니다.');</script>";
  ?>
  <meta http-equiv="refresh" content="0 url=one.php" />
