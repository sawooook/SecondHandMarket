
<?php
    $con = mysqli_connect('localhost', 'root', '136253', 'oo');
     $userID = $_POST['userID'];
     $statement = mysqli_prepare($con, "SELECT * FROM topic WHERE u_id = ?");
     mysqli_stmt_bind_param($statement, "s", $userID);
     mysqli_stmt_execute($statement);
     mysqli_stmt_store_result($statement);
     mysqli_stmt_bind_result($statement, $userID);
     $response = array();
     $response["success"] = true;
     while(mysqli_stmt_fetch($statement)){
       $response["success"] = false;//회원가입불가를 나타냄
       $response["userID"] = $userID;
     }
     //데이터베이스 작업이 성공 혹은 실패한것을 알려줌
     echo json_encode($response);
?>
