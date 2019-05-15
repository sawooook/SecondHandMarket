<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="UTF-8">
	<title> </title>
<style media="screen">
*{
margin: 0px;
box-sizing: content-box;
}

.clear{
clear:both;
}
.blue{
float: left;
height: 300px;
color: white;
background-color: blue;
width:300px;
}
.top{
height: 100px;
background-color: orange;
width: 100%;
}
.main-content{
width:900px;
margin-left: auto;
margin-right: auto;
background-color: gray;
}


/*clearfix div를 만드는 방법 말고 아래와 같이 할 수도 있음.*/

/*.main-content:after {
	content: " ";
	display: block;
	clear: both;
}*/

.bott{
width:100%;
height: 100px;
background-color: red;
}
</style>
</head>
<body>
	<h1> </h1>
	<div class="top"></div>
	<div class="main-content">
		<div class="blue"></div>
		<div class="blue"></div>
		<div class="blue"></div>
		<div class="blue"></div>
		<div class="blue"></div>
		<div class="blue"></div>
   	<div class="clear"></div>
	</div>
	<div class="bott"></div>
</body>
</html>


</script>
<script language="JavaScript">
<!-- Hide from old browsers
if (navigator.appName == "Netscape") {
document.captureEvents(Event.MOUSEDOWN)
document.onmousedown = checkClick

function checkClick(ev) {
        if (ev.which != 1) {
                alert('보안상 마우스 오른쪽 버튼은 사용할수 없습니다.')
                return false
        }
}
}


// -->
</script>
