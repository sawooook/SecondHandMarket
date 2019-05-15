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
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

  <!-- 부가적인 테마 -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

  <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

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
    #nav { width: 100%; float: left; margin: 0 0 0 0; padding: 0; background-color: #fff;  border-bottom: 1.5px solid #ccc; }
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
#write{
  padding: 1px 5px 5px 1290px;
}

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
#page_num {
	font-size: 14px;
	margin-left: 260px;
	margin-top:30px;
}
#page_num ul li {
	float: left;
	margin-left: 10px;
	text-align: center;
}
.fo_re {
	font-weight: bold;
	color:red;
}


ul{
   list-style:none;
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

li{

font-size: 20px;

list-style: none;
}
li a{

color: black;
}

    </style>

 </head>
 <body>
   <div class="navbar-wrapper">



    <nav class="navbar-inverse navbar-static-top">
         <div class="container">

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

  <img src="p12.png" height="225.9px">
      </div>



      <nav class="navbar navbar-default">


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


         <div id="pcolorr">
           <br>
           <br>
 <h1>후기게시판</h1>
 <h4>강의를 수강하신 한방한국사님들의 솔직한 후기입니다.</h4>
 <h4>수강후기를 남겨주신분들께는 해당강좌를 1주일 연장해드립니다</h4>

 <h5>근거없는 비방이나,도배,욕설시 관리자에 의해 삭제될 수 있습니다.</h5>

<hr style="width:65%;"></hr>
</div>







<div id=cc>
 <div id="board_area">
<table class="list-table">
	<thead>
    	<tr>
        	<th width="70">No</th>
            <th width="500">제목</th>
            <th width="120">작성자</th>
            <th width="100">작성일</th>
            <th width="40">조회수</th>
        </tr>
    </thead>
    <?php

    $conn = mysqli_connect("localhost","root","136253","hihi");
    error_reporting(E_ALL);
    ini_set('display_errors',1);

if(isset($_GET['page'])){
  $page= $_GET['page'];
}else{
  $page=1;
}
  $sql2 = "SELECT * FROM board";
  $result2= mysqli_query($conn,$sql2);
  $row=mysqli_num_rows($result);
  $list = 15;
  $block_ct = 5;

  $block_num = ceil($page/$block_ct); // 현재 페이지 블록 구하기
  $block_start = (($block_num - 1) * $block_ct) + 1; // 블록의 시작번호
  $block_end = $block_start + $block_ct - 1; //블록 마지막 번호

  $total_page = ceil($row / $list); // 페이징한 페이지 수 구하기
  if($block_end > $total_page) $block_end = $total_page; //만약 블록의 마지박 번호가 페이지수보다 많다면 마지박번호는 페이지 수
  $total_block = ceil($total_page/$block_ct); //블럭 총 개수
  $start_num = ($page-1) * $list; //시작번호 (page-1)에서 $list를 곱한다.
  $sql3 = "select * from board order by id desc limit $start_num, $list";
  $result3=mysqli_query($conn,$sql3);


  while($board=mysqli_fetch_array($result3)){
		$title['title']=$board['title'];
    $title['id']=$board['id'];
    $title['date']=$board['date'];
    $title['name']=$board['name'];
    $title['content']=$board['content'];


          			?>
	<tbody>
		<tr>
            <td width="70"><?php echo $board['id']?></td>

              <td width="70">
               <a href ="read.php?id=<?=$title['id']?>">
              <?=$board['title']?></a></td>

            <td width="120"><?php echo $board['name']?></td>
            <td width="100"><?php echo $board['date']?></td>
             <td width="100"><?php echo $board['hit'] ?></td>

        </tr>
	</tbody>
	<?php } ?>
</table>
<div id="page_num">
      <ul>
        <?php
          if($page <= 1)
          { //만약 page가 1보다 크거나 같다면
            echo "<li class='fo_re'>처음</li>"; //처음이라는 글자에 빨간색 표시
          }else{
            echo "<li><a href='?page=1'>처음</a></li>"; //알니라면 처음글자에 1번페이지로 갈 수있게 링크
          }
          if($page <= 1)
          { //만약 page가 1보다 크거나 같다면 빈값

          }else{
          $pre = $page-1; //pre변수에 page-1을 해준다 만약 현재 페이지가 3인데 이전버튼을 누르면 2번페이지로 갈 수 있게 함
            echo "<li><a href='?page=$pre'>이전</a></li>"; //이전글자에 pre변수를 링크한다. 이러면 이전버튼을 누를때마다 현재 페이지에서 -1하게 된다.
          }
          for($i=$block_start; $i<=$block_end; $i++){
            //for문 반복문을 사용하여, 초기값을 블록의 시작번호를 조건으로 블록시작번호가 마지박블록보다 작거나 같을 때까지 $i를 반복시킨다
            if($page == $i){ //만약 page가 $i와 같다면
              echo "<li class='fo_re'>[$i]</li>"; //현재 페이지에 해당하는 번호에 굵은 빨간색을 적용한다
            }else{
              echo "<li><a href='?page=$i'>[$i]</a></li>"; //아니라면 $i
            }
          }
          if($block_num >= $total_block){ //만약 현재 블록이 블록 총개수보다 크거나 같다면 빈 값
          }else{
            $next = $page + 1; //next변수에 page + 1을 해준다.
            echo "<li><a href='?page=$next'>다음</a></li>"; //다음글자에 next변수를 링크한다. 현재 4페이지에 있다면 +1하여 5페이지로 이동하게 된다.
          }
          if($page >= $total_page){ //만약 page가 페이지수보다 크거나 같다면
            echo "<li class='fo_re'>마지막</li>"; //마지막 글자에 긁은 빨간색을 적용한다.
          }else{
            echo "<li><a href='?page=$total_page'>마지막</a></li>"; //아니라면 마지막글자에 total_page를 링크한다.
          }
        ?>
      </ul>
    </div>
  	</div>


 </div>
 <form action="add.php" method="post">
 <div id=write>
 <button class="myButton">글쓰기</button>
 </div>
 </form>
 <br>
 <br>
 <br>
 <br>
<br>
</body>
</html>
