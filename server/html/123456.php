<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <title>Float Grid</title>
  <style media="screen">
  *{
 box-sizing: border-box;
 margin: 0;
 padding: 0;
}
body{
 background-image: url("https://i.postimg.cc/cZnGkT8X/Composed-layer-1-X.png");
 background-repeat: no-repeat;
 background-size: cover;
}

.saouk1 img{
  width: 559px;
}
.saouk1{
 margin-right: auto;
 width: 1118px;
 margin-left: auto;
}

.saouk1{
  margin-bottom: 201px;
 margin-top: 342px;
}

content: '';
.clearfix::after{
 clear: both;
 display: block;
}

img {
 float: left;
}

.saouk2{
 width: 1118px;
 margin-left: auto;
 margin-right: auto;
}
.saouk2 img:first-child, .content2 img:last-child{
 width: 1118px;
}
.saouk2 img:nth-child(2), .content2 img:nth-child(3){
 width: 559px;
}
.saouk2{
 margin-bottom: 200px;
}
#container{
   width: 1118px;
   margin: 0 auto;
}

  </style>
</head>
<body>
  <div id="container">

    <div class="saouk1 clearfix">
      <img src="https://i.postimg.cc/PqnP8qDZ/img1.jpg">
      <img src="https://i.postimg.cc/YSNjNwNP/img2.jpg">
      <img src="https://i.postimg.cc/9MJMStnt/img3.jpg">
      <img src="https://i.postimg.cc/zBJv0P0G/img4.jpg">
    </div>

    <div class="saouk2 clearfix">
        <img src="https://i.postimg.cc/L4Bf6Lhw/05-1X.png" alt="">
        <img src="https://i.postimg.cc/DyxX1hrc/07-1X.png" alt="">
        <img src="https://i.postimg.cc/jdxfW275/08-1X.png" alt="">
        <img src="https://i.postimg.cc/vmgfQK6t/06-1X.png" alt="">
    </div>

  </div>

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
