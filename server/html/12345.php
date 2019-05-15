<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="UTF-8">
	<title>Grid - Float </title>
<style media="screen">
*{
box-sizing: content-box;
margin: 0px;
}

.header{
width: 100%;
height: 100px;
background-color: orange;
}
.main-content{
width:900px;
background-color: gray;
margin-left: auto;
margin-right: auto;
}
.item{
width:300px;
height: 300px;
float: left;
background-color: blue;
color: white;
}
.clearfix{
clear:both;
}

/*clearfix div를 만드는 방법 말고 아래와 같이 할 수도 있음.*/

/*.main-content:after {
	content: " ";
	display: block;
	clear: both;
}*/

.footer{
width:100%;
height: 100px;
background-color: red;
}
</style>
</head>
<body>

	<div class="header"></div>
	<div class="main-content">
		<div class="item"></div>
		<div class="item"></div>
		<div class="item"></div>
		<div class="item"></div>
		<div class="item"></div>
		<div class="item"></div>
   	<div class="clearfix"></div>
	</div>
	<div class="footer"></div>
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
