<?php
$conn = mysqli_connect('localhost','root','136253','hihi');

$idch = $_GET['id'];

$ss=setcookie("hello?{$_GET['id']}","hh",time() +10, "/");


    $sql = "SELECT * FROM board where id = {$_GET['id']}";
    $result=mysqli_query($conn,$sql);
    $row = mysqli_fetch_array($result);

if(!isset($_COOKIE["hello?{$_GET['id']}"])){
    $hit = "SELECT * FROM board where id = {$_GET['id']}";
    $hit_result=mysqli_query($conn,$hit);
    $hit_row = mysqli_fetch_array($hit_result);
    $hit_row['hit']=$hit_row['hit']+1;
    $hit_update = mysqli_query($conn,"update board set hit ={$hit_row['hit']} where id ={$_GET['id']} ");
}else{

}



?>


<!doctype html>
<html lang="ko">
 <head>
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

   <!-- 부가적인 테마 -->
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

   <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
   <style media="screen">

   div#ho{
     padding: 0px 0px 0px 480px;
   }

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
          text-align: center;
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
.dap_ins {margin-top:50px; }
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

   div#sss{
        padding:1px 5px 600px 1px;
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
   li{

   font-size: 20px;

   list-style: none;
   }
   li a{

   color: black;
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
#ccc{

  padding: 0px 170px 0px 0px;
}
.dap_lo {font-size: 14px; padding:10px 0 10px 0; border-bottom: solid 1px gray; width:70%;}
   #nav_menu ul li:first-child {
   border-left: none;
   }
   #board_read {width:900px; font-family:'malgun gothic','Sans-Serif','arial'; margin: 0 auto;  position: relative; word-break:break-all; }
   #user_info {font-size:14px;}
   #user_info ul li {float:left; margin-left:10px; }
   #bo_line {width:880px; height:2px; background: gray; margin-top:20px;}
   #bo_content {margin-top:20px; }
   #bo_ser {padding: 0px 0px 0px 1130px; font:14px 'malgun gothic','Sans-Serif','arial'; color:#333;  position: absolute; right:; }
   #bo_ser > ul > li {float:left; margin-left:10px;}
   a {text-decoration: none; color:#333;}
ul li {list-style:none; }

#bbb{
  padding: 0px 0px 0px 40px;

}
   #board_read {width:700px; position:relative; top:30px; }
   .fl {float:left; }
   .tc {text-align:center; }
   .w5 {width:50px; }
   .w17 {width:170px; }
   .w7 {width:70px; }
   .w10 {width:10px; }
   .w100 {width:100px; }
   .read {width:10%; background:#bfbfbf; height:40px; font:bold 13px "Malgun Gothic"; line-height:40px; color:black; text-align:center;  }
   .read_c {background-color:#bfbfbf; }
   .read_con {width:90%; height:40px; float:left; font:normal 13px "Dotum"; line-height:40px; color:#333333; border-bottom:solid 1px black; }
   .read_nl {width:10%; background:#bfbfbf; height:130px; font:bold 13px "Malgun Gothic"; line-height:130px; color:black; text-align:center; }
   .read_nl_con {width:89%; height:129px; float:left; font:normal 13px "Dotum"; line-height:20px;  }
   .bo_ser {font:14px 'dotum'; color:#333;  position:absolute; top:380px; }
   .bo_ser > ul > li {float:left; margin-left:10px; }
   </style>
  <meta charset="UTF-8">
  <title>게시판</title>

 </head>
 <body>
   <div class="navbar-wrapper">



       <nav class="navbar-inverse navbar-static-top">
         <div class="container">
           <div class="navbar-header">


           </div>
           <div id="navv" class="navbar-collapse collapse">
             <ul class="navbar-nav">

               <?php
               session_start();

               if(isset($_SESSION['userid'])){

               ?>
                   <li><a href="product_cart.php">장바구니</a></li>
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
         </div>



         <nav class="navbar navbar-default">

             <div class="navbar-header">


             </div>
             <div id="navbar" class="navbar-collapse collapse">
               <ul class="nav nav-justified">
                 <li><a href="mainpage.php">HOME</a></li>
                 <li><a href="hanguk.php?hanguksa">한국사 능력 검정시험이란?</a></li>
                 <li><a href="product.php?">수강신청</a></li>
                 <li><a href="one.php?question">후기게시판</a></li>
                 <li><a href="mypage.php?question">마이페이지</a></li>
               </ul>

             </div>

         </nav>


      <br>
      <br>
      <br>
      <br>
         <div id="pcolorr">
           <br>
           <br>
 <h1>후기게시판</h1>
 <h4>강의를 수강하신 한방한국사님들의 솔직한 후기입니다.</h4>
 <h4>수강후기를 남겨주신분들께는 해당강좌를 1주일 연장해드립니다</h4>
 <h5>근거없는 비방이나,도배,욕설시 관리자에 의해 삭제될 수 있습니다.</h5>

</div>

<div id="ccc">
<div id="board_read">
  <h2><?php echo $row['title']; ?></h2>
    <div id="user_info">
      <?php echo $row['name']; ?> <?php echo $row['date']; ?> 조회:<?php echo $row['hit']; ?>
        <div id="bo_line"></div>
      </div>
      <div id="bo_content">
        <?php echo nl2br("$row[content]"); ?>
        <?php
        if(isset($row['file'])){
          echo "<img src='{$row['file']}' />";
        }else {
          echo "호우";
        }?>
        <br><br><br><br><br>
          <br><br><br><br><br>

</div>
      </div>


      <!--- 댓글 불러오기 -->
      <div id="ho">
      <div class="reply_view">
      	<h3>댓글목록</h3>
        댓글을 달아주세요

      		<?php
          $conn =mysqli_connect('localhost','root','136253','hihi');
          $sql2 = "SELECT * from reply where con_num={$_GET['id']} order by ind asc";


          $result=mysqli_query($conn,$sql2);

          while($reply=mysqli_fetch_array($result)){
          ?>


      		<div class="dap_lo">
      			<div><b><?php echo $reply['name'];?></b></div>
      			<div class="dap_to"><?php echo nl2br("$reply[content]"); ?></div>
      			<div class="rep_me dap_to"><?php echo $reply['date']; ?></div>
            <?php
            session_start();
           if($_SESSION['userid']==$reply['name']){

            ?>

            <div class="rep_me">

		          <a href="reply_update.php?id=<?= $reply['ind']?>" >수정</a>
      			<a href="reply_delete.php?id=<?= $reply['ind']?>"   onclick="return confirm('정말 삭제 하시겠습니까?');">삭제</a>


            </div>

            <?php
    }
           ?>
      		</div>

      	<?php

}
       ?>
      	<div class="dap_ins">
      		<ul>
      			<li class="fl"><textarea name="content" class="reply_content" id="re_content" cols="80" rows="3" required="required"></textarea></li>
            <?php
            session_start();
            if(isset($_SESSION['userid'])){

               ?>
            <li><input type="button" id="rep_bt" value="댓글" class="re_bt" onclick="reply_ok();" /></li>
          <?php } ?>
          </ul>
      	</div>
          </div>
      </div><!--- 댓글 불러오기 끝 -->
  <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>

      <script type="text/javascript">

      function reply_ok(){
      	var rep_content = $("#re_content").serialize();

      		$.ajax({
      			type: 'post',
      			url: 'replyok.php?id=<?php echo $row["id"]; ?>',

      			data :rep_content,

      			success: function(data){
      				$(".reply_view").html(data);
      				$(".reply_content").val('');
      			}

      		});
      	}
      </script>

<div id="pcolorr">
<br><br><br><br>
 <hr width="75%;">
</div>
  <div id="bo_ser">

    <ul>

        <br><br><br><br><br>
      <li><a href="one.php" class="myButton">목록</a></li>
      <?php
      session_start();

        if($_SESSION['userid']==$row['name']){


          ?>

      <li><a href="update.php?id=<?=$row['id']?>"class="myButton">수정</a></li>
      <?php

    }

       if($_SESSION['userid']==$row['name']){

         ?>

      <li><a href="delete.php?id=<?=$row['id']?>" class="myButton" onclick="return confirm('정말 삭제 하시겠습니까?');">삭제</a></li>
      <?php

    }
     ?>




        <br><br><br><br><br>
          <br><br><br><br><br>
    </ul>
  </div>
</div>
 </body>
</html>
