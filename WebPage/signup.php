
<?php
$conn=mysqli_connect('localhost','root','136253','hihi');


$ran= "";
for( $i=0; $i<4; $i++) //7자리만 출력
{
     if( rand(0,1) ) $ran .= rand( 0, 10 );
     else $ran .= chr(rand( 97, 122 ));
}



 ?>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <style type="text/css">
      div{
        text-align: center;
      }
      h1#ss{
        background-color: : red;
    }
    div#s_left{
      text-align: center;
      width: 700px;
      height: 200px;
      background-color: yellow;
      float: left;
    }
    div#le{

  padding: 0px 0px 0px 95px;
    }
    div#lee{

  padding: 0px 0px 0px 110px;
    }

    div#leee{

  padding: 0px 0px 0px -10px;
    }

      #nav { width: 100%; float: left; margin: 0 0 1em 0; padding: 0; background-color: #fff;  border-bottom: 1.5px solid #ccc; }
      #nav ul { list-style: none; width: 600px; margin: 0 auto; padding: 1px 5px 5px 1px; }
       #nav li { float: left; }
        #nav li a { display: block;padding: 15px 30px; margin: 8px 0px 8px 0px; text-decoration: none; font-size: 14px; font-weight: bold; color: #000; border-right: 1px solid #ccc; }
        #nav li:first-child a { border-left: 1px  solid #ccc; }
         #nav li a:hover { color: #c00; background-color: #000; }


      #navv ul { list-style: none; width: 300px; margin: 0 auto; padding: 0; }
     #navv { float: right; }
    #navv li a{display: block; padding: 16px 15px; text-decoration: none; font-size: 13px; color: #fff; border-right: 1px ;}
    #navv li a:hover { color: #c00; background-color: #000; }
      #navv li:first-child a { border-left: none; }

      li{

      font-size: 20px;

      list-style: none;
      }
      li a{

      color: black;
      }

    </style>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script language="javascript">

    function OpenWindow(url,intWidth,intHeight) {
       window.open("write_ok.php", "window_name", "width=430,height=500,location=no,status=no,scrollbars=yes") ;
    }

    function jusoCallBack(roadFullAddr){
      document.form.roadFullAddr.value =roadFullAddr;
    }
    </script>
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



    <div id="wrap">

      <img src="p12.png" height="225.9px">
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
                  <li><a href="mypage.php">마이페이지</a></li>
                </ul>

              </div>

          </nav>



  <center>
    <div class="container">

    <legend>

  <br>

    회원가입


      <br>


    </legend>
  </div>
      <br>
      <br>
      <br>


      <form name="userinput" action="process_signup.php" method="POST">

    <input type="text" name="id" id="id" required="required" size="40" placeholder="아이디를 입력해주세요"/>
    <div id="loadtext"></div>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript">
        $("#id").blur(function(){
            var id = $('#id').val();

            $.ajax({
            type: "POST",  //나는 post로 보냄
            url: "idcheck.php", //
            data: "id="+ id ,
            cache: false, //데이터 저장할지 안할지
            success: function(data){
                $("#loadtext").html(data);
            }
            });
        });
    </script>


    <br>

    <input type="password"  dir="ltr" name="passwd" required="required" size="40" placeholder="비밀번호를 입력해주세요"/>

    <br><br>
    <input type="text"  dir="ltr"name="nickname" required="required" size="40" placeholder="닉네임을 입력해주세요"/>

    <br><br>

        <div id="le">
            <input type="text" id="re_content" size="40" required="required"  placeholder="이메일을 입력해주세요" name="email" >
  <input type="button" name="submit" value="이메일전송" onclick="reply_ok();" >
        </div>

        <input type="hidden" id="hidden" name="hidden" value="<?=$ran?>">

        <script type="text/javascript">

        function reply_ok(){
        var re_content = $("#re_content").val();
        var hidden = $("#hidden").val();
        var alldata={"so":re_content,"soso":hidden};
            $.ajax({
              type: 'post',
              url: 'create.php',
              data :alldata

            });

        alert('이메일전송완료');}



        </script>
        <div id="loadtext3"></div>
        <div id="le">
          <br>
        <input type="text" id="hihi" size="40"  name="hihi" >
        <input type="button" name="submit" value="이메일인증"  placeholder="이메일 인증" onclick="reply_ok2();">
</div>

        <script type="text/javascript">

        function reply_ok2(){

        var hihi = $("#hihi").val();
        var hidden3 = $("#hidden").val();
        var alldata3={"so":hihi,"soso":hidden3};
            $.ajax({
              type: 'post',
              url: 'connection.php',
              data : alldata3,
                cache: false,
              success: function(data){
                  $("#loadtext3").html(data);
              }

            });

        }
        </script>
    <br>
<div id="lee">
    <input type="text" name="sample4_postcode" id="sample4_postcode" required="required" size="40" placeholder="우편번호">
    <input type="button" onclick="sample4_execDaumPostcode()"  value="우편번호 찾기"><br><br>
  </div>
  <div id="leee">

    <input type="text" id="sample4_roadAddress" size="18" placeholder="도로명주소">
    <input type="text" id="sample4_jibunAddress" size="18" placeholder="지번주소">

  </div>

<span id="guide" style="color:#999"></span>
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script><script charset="UTF-8" type="text/javascript" src="http://t1.daumcdn.net/postcode/api/core/180619/1529384927473/180619.js"></script>
    <script>

        function sample4_execDaumPostcode() {
            new daum.Postcode({
                oncomplete: function(data) {

                    var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
                    var extraRoadAddr = ''; // 도로명 조합형 주소 변수


                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraRoadAddr += data.bname;
                    }
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                       extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    if(extraRoadAddr !== ''){
                        extraRoadAddr = ' (' + extraRoadAddr + ')';
                    }

                    if(fullRoadAddr !== ''){
                        fullRoadAddr += extraRoadAddr;
                    }


                    document.getElementById('sample4_postcode').value = data.zonecode; //5자리 새우편번호 사용
                    document.getElementById('sample4_roadAddress').value = fullRoadAddr;
                    document.getElementById('sample4_jibunAddress').value = data.jibunAddress;

                    if(data.autoRoadAddress) {

                        var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                        document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

                    } else if(data.autoJibunAddress) {
                        var expJibunAddr = data.autoJibunAddress;
                        document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';

                    } else {
                        document.getElementById('guide').innerHTML = '';
                    }
                }
            }).open();
        }
    </script>
    <br>
    <br>

    <br>

    <input type="submit" name="signup_button" value="Sign up" style="height:35px; width:200px;"
onclick="return blank_up()">
  </form>

</div>

<br>
<br>




<br>
<br>



  </center>
    <br>




  <br>
  <br>





  </body>
</html>

<script type="text/javascript">
function blank_up(){

    if(document.userinput.hihi.value == ''){
          alert("이메일중복확인을 해주세요");
        document.userinput.hihi.focus();
        return false;
      }

}


</script>
