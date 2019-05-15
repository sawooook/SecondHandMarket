<!--- 게시글 수정 -->
<?php
$conn = mysqli_connect(
  'localhost',
  'root',
  '136253',
  'hihi'
);
  $sql= "SELECT * from board WHERE id={$_GET['id']}";
  $result = mysqli_query($conn,$sql);
  $board = mysqli_fetch_array($result);


?><!doctype html>
<html lang="ko">
 <head>

  <meta charset="UTF-8">
  <title>게시판</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

  <!-- 부가적인 테마 -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

  <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style media="screen">


<style media="screen">
div#cc{
  padding: 0px 5px 5px 485px;
}
* {margin:0; padding:0 ;}
a {text-decoration: none; color:#333;}
#board_area {margin-top:10px;}
.list-table thead th{
 height:40px; border-top:2px solid #09C; text-align:center;border-bottom:1px solid #CCC; font:bold 17px
 'malgun gothic';  }
.list-table tbody td{
 text-align:center; padding:10px 0; border-bottom:1px solid #CCC; height:20px; font: 14px
  'malgun gothic';}
  #nav { width: 100%; float: left; margin: 0 0 1em 0; padding: 0; background-color: #fff;  border-bottom: 1.5px solid #ccc; }
  #nav ul { list-style: none;  width: 600px; margin: 0 auto; padding: 1px 5px 5px 1px; }
   #nav li { float: left; }
    #nav li a { display: block;padding: 15px 30px; margin: 8px 0px 8px 0px; text-decoration: none; font-size: 14px; font-weight: bold; color: #000; border-right: 1px solid #ccc; }
    #nav li:first-child a { border-left: 1px  solid #ccc; }
     #nav li a:hover { color: #c00; background-color: #000; }
     #navv ul { list-style: none; width: 300px; margin: 0 auto; padding: 0; }
    #navv { float: right; }
   #navv li a{display: block; padding: 16px 15px; text-decoration: none; font-size: 13px; color: #fff; border-right: 1px ;}
   #navv li a:hover { color: #c00; background-color: #000; }
     #navv li:first-child a { border-left: none; }
     #pcolor{
    
     }

     #pcolorr{
      padding: 1px 5px 5px 490px;
     }
     #pcolorrr{
         padding: 1px 5px 5px 470px;
     }
#write{
padding: 1px 5px 5px 1290px;
}

#boardWrite table {width:720px}
#boardWrite th {width:100px;padding:5px;text-align:right;vertical-align: top}
#boardWrite td {width:620px;padding:5px}

#boardWrite #bID {width:180px}
#boardWrite #bPassword {width:180px}
#boardWrite #bTitle {width:400px}
#boardWrite #bContent {width:550px;height:300px}

#boardWrite .btnSet {text-align:center}
.myButton {
-moz-box-shadow:inset 0px 1px 0px 0px #54a3f7;
-webkit-box-shadow:inset 0px 1px 0px 0px #54a3f7;
box-shadow:inset 0px 1px 0px 0px #54a3f7;
background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #007dc1), color-stop(1, #0061a7));
background:-moz-linear-gradient(top, #007dc1 5%, #0061a7 100%);
background:-webkit-linear-gradient(top, #007dc1 5%, #0061a7 100%);
background:-o-linear-gradient(top, #007dc1 5%, #0061a7 100%);
background:-ms-linear-gradient(top, #007dc1 5%, #0061a7 100%);
background:linear-gradient(to bottom, #007dc1 5%, #0061a7 100%);
filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#007dc1', endColorstr='#0061a7',GradientType=0);
background-color:#007dc1;
-moz-border-radius:3px;
-webkit-border-radius:3px;
border-radius:3px;
border:1px solid #124d77;
display:inline-block;
cursor:pointer;
color:#ffffff;
font-family:Arial;
font-size:13px;
padding:6px 24px;
text-decoration:none;
text-shadow:0px 1px 0px #154682;
}
.myButton:hover {
background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #0061a7), color-stop(1, #007dc1));
background:-moz-linear-gradient(top, #0061a7 5%, #007dc1 100%);
background:-webkit-linear-gradient(top, #0061a7 5%, #007dc1 100%);
background:-o-linear-gradient(top, #0061a7 5%, #007dc1 100%);
background:-ms-linear-gradient(top, #0061a7 5%, #007dc1 100%);
background:linear-gradient(to bottom, #0061a7 5%, #007dc1 100%);
filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#0061a7', endColorstr='#007dc1',GradientType=0);
background-color:#0061a7;
}
.myButton:active {
position:relative;
top:1px;
}


li{

font-size: 20px;

list-style: none;
}
li a{

color: black;
}

     #nav_menu ul {
list-style-type:none;




background-color:#000000;
padding:1px 5px 5px 1px;



float:right;
}
#nav_menu ul li {
display:inline;
border-left: 1px solid #c0c0c0;
padding: 0px 10px 0px 10px;
margin: 5px 0px 5px 0px;



/*흰 글씨와 9포인트의 글자로 변경함 */
color:#ffffff;
font-size:9pt;
}



#nav_menu ul li:first-child {
border-left: none;
}

</style>
 </head>
 <body>
   <div class="navbar-wrapper">



       <nav class="navbar-inverse navbar-static-top">
         <div class="container">
           <div class="navbar-header">
             <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
               <span class="sr-only">Toggle navigation</span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
             </button>

           </div>
           <div id="navv" class="navbar-collapse collapse">
             <ul class="navbar-nav">

               <?php
               session_start();

               if(isset($_SESSION['userid'])){

               ?>
                               <li><a href="logout.php?board">로그아웃</a></li>
                               <?php }  else{ ?>



                               <li><a href="login.php">로그인</a></li>
                               <?php }  ?>
                               <li><a href="signup.php?signup"> 회원가입</a></li>

               </li>
             </ul>
           </div>
         </div>
       </nav>


   </div>
   <div id="pcolor">
     <img src="p12.png" height="225.99px">

           <br>

           <nav class="navbar navbar-default">

               <div class="navbar-header">


               </div>
               <div id="navbar" class="navbar-collapse collapse">
                 <ul class="nav nav-justified">
                   <li><a href="mainpage.php">HOME</a></li>
                   <li><a href="hanguk.php?hanguksa">한국사 능력 검정시험이란?</a></li>
                   <li><a href="product.php?">수강신청</a></li>
                   <li><a href="one.php?question">후기게시판</a></li>
                 </ul>

               </div>

           </nav>



         <div id="pcolorr">
           <br>
           <br>
 <h1>후기게시판</h1>
 <h4>강의를 수강하신 한방한국사님들의 솔직한 후기입니다.</h4>
 <h4>수강후기를 남겨주신분들께는 해당강좌를 1주일 연장해드립니다</h4>

 <h5>근거없는 비방이나,도배,욕설시 관리자에 의해 삭제될 수 있습니다.</h5>

<hr style="width:65%;"></hr>
</div>
<article class="boardArticle">


<div id ="pcolorrr">
		<div id="boardWrite">

      <form action="process_update.php?id=<?php echo $board['id']?>" method="post" enctype="multipart/form-data">
      <input type="hidden" name="update_id" value="<?=$_GET['id']?>">
				<table id="boardWrite">


					<tbody>
						<tr>
							<th scope="row"><label for="uname">ID</label></th>

							<td class="id"><input type="text" value="<?php echo $board['name'] ?>" name="update_name"size="40" required readonly></td>

						</tr>



						<tr>

							<th scope="row"><label for="Title">제목</label></th>

							<td class="title"><input type="text" value="<?php echo $board['title']?>" name="update_title" size="40" required></td>

						</tr>

						<tr>

							<th scope="row"><label for="Content">내용</label></th>

							<td class="content"><textarea name="update_content" rows="18" cols="80" required><?php echo $board['content'] ?></textarea></td>

						</tr>

					</tbody>

				</table>


				<div class="btnSet">

					<button type="submit" class="myButton">작성</button>

					<a href="one.php" class="myButton">목록</a>

        <input type="file" value="1" name="file" />

				</div>

			</form>

		</div>

	</article>

</div>
<br>
<br>
<br><br>

    </body>
</html>
