<?php
$ran= "";
for( $i=0; $i<10; $i++) //7자리만 출력
{
     if( rand(0,1) ) $ran .= rand( 0, 9 ); //숫자
     else $ran .= chr(rand( 97, 122 )); //영어소문자
}
echo $ran;
?>

    <input type="text" id="re_content" size="24"  placeholder="이메일을 입력해주세요" name="re_content">
    <input type="hidden" id="hidden" name="hidden" value="<?=$ran?>">
    <input type="button" name="submit" value="submit" onclick="reply_ok();">



<br><br>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>

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
<div id="loadtext">ddd</div>
<input type="text" id="hihi" size="24"  name="hihi">
<input type="button" name="submit" value="submit" onclick="reply_ok2();">


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
          $("#loadtext").html(data);
      }

    });
alert('굿둥이');
}
</script>
