<?php
$conn = mysqli_connect(
  'localhost',
  'root',
  '136253',
  'hihi');
$sql = "SELECT * FROM board";
$result = mysqli_query($conn,$sql);
?>

<!doctype html>
<html lang="ko">
 <head>
  <meta charset="UTF-8">
  <title>게시판</title>
  <link rel="stylesheet" href="/style.css" />
 </head>
 <body>
 <h1><a href="/one.php">자유게시판</a></h1>
 <h4>글쓰기</h4>
        <div id="board_write">

          <form action="write_ok.php" method="post">


                    <table id="boardWrite">
                        <tr>
                            <td class="tb"><label for="uname">이름</label></td>
                            <td height="30"><input type="text" name="name" id="uname" size="50" class="inh"/></td>
                        </tr>
                        <tr>
                            <td class="tb"><label for="upw">비밀번호</label></td>
                            <td height="30"><input type="password" name="pw" id="upw" size="50"/></td>
                        </tr>
                        <tr>
                            <td class="tb"><label for="utitle">제목</label></td>
                            <td height="30"><input type="text" name="title" id="utitle" size="50"/></td>
                        </tr>
                        <tr>
                            <td class="tb"><label for="ucontent">내용</label></td>
                            <td height="30"><textarea name="content" id="ucontent" rows="10" cols="37"></textarea></td>
                        </tr>
                    </table>

                    <button>작성</button>

            </form>
        </div>
    </body>
</html>
