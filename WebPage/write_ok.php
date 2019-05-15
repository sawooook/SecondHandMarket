<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
dd
<head> <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<?
$ADDR['inputYn'] = $_POST['inputYn'];
$ADDR['roadFullAddr'] = $_POST['roadFullAddr'];
$ADDR['roadAddrPart1'] = $_POST['roadAddrPart1'];
$ADDR['roadAddrPart2'] = $_POST['roadAddrPart2'];
$ADDR['engAddr'] = $_POST['engAddr'];
$ADDR['jibunAddr'] = $_POST['jibunAddr'];
$ADDR['zipNo'] = $_POST['zipNo'];
$ADDR['addrDetail'] = $_POST['addrDetail'];
$ADDR['admCd'] = $_POST['admCd'];
$ADDR['rnMgtSn'] = $_POST['rnMgtSn'];
$ADDR['bdMgtSn'] = $_POST['bdMgtSn'];
?>
</head>
<script language="javascript">
function init(){
var url = location.href;
var confmKey = "U01TX0FVVEgyMDE4MDYyMDE2NTQwNjEwNzk1Mzg=";
var resultType = "4";
var inputYn= "<?=$ADDR['inputYn']?>";
if(inputYn != "Y"){
document.form.confmKey.value = confmKey;
document.form.returnUrl.value = url;
document.form.resultType.value = resultType;
document.form.action="http://www.juso.go.kr/addrlink/addrLinkUrl.do"; //인터넷망(행정망의 경우 별도 문의)
document.form.submit();

}else{
//2017년 2월 제공항목이 확대되었습니다. 원하시는 항목을 추가하여 사용하시면 됩니다.
opener.jusoCallBack("<?=$ADDR[roadFullAddr]?>","<?=$ADDR[roadAddrPart1]?>","<?=$ADDR[addrDetail]?>","<?=
$ADDR[roadAddrPart2]?>","<?=$ADDR[engAddr]?>","<?=$ADDR[jibunAddr]?>","<?=$ADDR[zipNo]?>",
"<?=$ADDR[admCd]?>", "<?=$ADDR[rnMgtSn]?>", "<?=$ADDR[bdMgtSn]?>");
window.close
}
}
</script>

<body onload="init();">

<form action="form" name="form" method="post">
<input type="hidden" id="confmKey" name="confmKey" value=""/>
<input type="hidden" id="returnUrl" name="returnUrl" value=""/>
<input type="hidden" id="resultType" name=" resultType " value=""/>

<!-- 해당시스템의 인코딩타입이 EUC-KR일경우에만 추가 START-->
<!--input type="hidden" id="encodingType" name="encodingType" value="EUC-KR"/-->
<!-- 해당시스템의 인코딩타입이 EUC-KR일경우에만 추가 END-->
</form> </body>
</html>
