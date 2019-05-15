<!DOCTYPE html>
<html lang="ko-KR">
<head>
    <meta charset="UTF-8">
    <title>Floating layout - Gallery</title>
    <link href="https://fonts.googleapis.com/css?family=Lora" rel="stylesheet">
    <style media="screen">

    *{
         box-sizing: border-box;
         margin: 0px;
         padding: 0px;
     }
    .col{
  height: 120px;
  /* float: left; */
  margin-top: auto;
  margin-bottom: auto;
  }

  h1 {
     text-align: center;
     margin: auto;
     height: 120px;
     line-height: 120px;
     color: white;
     margin-top: auto;
     margin-bottom: auto;
     font-size: 64px;
     /* color: #ffffff; */
   }
   .gallery{
margin-left:auto;
margin-right: auto;
width: 1400px;
   }
   img{
    width: 350px;
    height: 350px;
    display: block;
    float: left;
}
img:hover{
    opacity: 2;
}
.clearfix{
    clear: both;
}

.hello{
  padding: 50px 140px;
  background-color: gray;
}
    </style>
</head>
<body>

    <div class="col" style="background-color: black"><h1 style="color:white">Gallery</h1></div>

<h1 style="color:white">Gallery</h1>

    <div class="gallery">
        <img src="https://i.postimg.cc/sxTTDKp0/photo-01.png" alt="">
        <img src="https://i.postimg.cc/BQvhw93W/photo-02.png" alt="">
        <img src="https://i.postimg.cc/wjC0v1mH/photo-03.png" alt="">
        <img src="https://i.postimg.cc/MKr9CPZn/photo-04.png" alt="">
        <img src="https://i.postimg.cc/26mGQgGK/photo-05.png" alt="">
        <img src="https://i.postimg.cc/0yMVPYrc/photo-06.png" alt="">
        <img src="https://i.postimg.cc/9Xb8pXvt/photo-07.png" alt="">
        <img src="https://i.postimg.cc/25nTBCQW/photo-08.png" alt="">
        <img src="https://i.postimg.cc/NM44pbPf/photo-09.png" alt="">
        <img src="https://i.postimg.cc/7PK9zs2K/photo-10.png" alt="">
        <img src="https://i.postimg.cc/gkrDHVym/photo-11.png" alt="">
        <img src="https://i.postimg.cc/xT7PxXgH/photo-12.png" alt="">
        <img src="https://i.postimg.cc/FHLV2hn6/photo-13.png" alt="">
        <img src="https://i.postimg.cc/QtXg91xL/photo-14.png" alt="">
        <img src="https://i.postimg.cc/q7cXvjrX/photo-15.png" alt="">
        <img src="https://i.postimg.cc/7hhgX42m/photo-16.png" alt="">
        <!-- <div class="clearfix"></div> -->
    </div>
    <div class="clearfix"></div>

    <div class="hello">
  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dicta aliquam sequi quae. Quis voluptatum consequuntur dolorem vitae nobis perspiciatis illum illo est, delectus, labore, suscipit deleniti natus dicta? Optio veritatis odit assumenda. Beatae modi corporis illo quo cum, ea eum reiciendis culpa esse vero ratione excepturi dolorem natus, odio voluptate dolores repellat, suscipit fugit illum! Quia consequatur dignissimos, a totam suscipit sed tempore rem velit ex, libero sint, eum, eaque eligendi odit. Autem eos dolore saepe, minus repellendus qui, quam perspiciatis ducimus nulla, debitis delectus totam laborum iste quibusdam sint blanditiis voluptas nihil dicta, quasi quia fugit. Alias, maxime, eligendi. Porro vel beatae placeat ea doloribus ipsa facilis deleniti ab. Alias vitae, voluptas laudantium, impedit ad veniam voluptate ipsum enim nemo veritatis unde deleniti, eum error quaerat? Accusantium numquam illum nobis fugiat modi voluptates quos odio repudiandae ea, eligendi officia? Ab qui amet non quae, illum omnis dolorem commodi quasi iusto nemo explicabo beatae voluptatibus eligendi veritatis labore molestias possimus minima sapiente! Tempora itaque dicta, minima vitae dolore suscipit maiores voluptatibus, eos expedita nihil quod reprehenderit quisquam maxime impedit et rem quasi ipsam distinctio. Totam id magnam blanditiis adipisci ab a corporis minus, soluta deserunt illo, dolorem praesentium sit eius. Mollitia qui velit omnis nobis, error cupiditate accusamus ut blanditiis consequuntur veniam ab magnam minus commodi odio facere at ducimus, iure nam sed molestias quibusdam aut nulla officia amet. Aperiam eos iste, fuga molestias explicabo quae cumque similique officiis aliquid error accusantium fugiat, consequuntur id! Qui, quas, reprehenderit. Quidem nulla cum, hic libero magnam dignissimos autem, itaque velit illum, tempora, pariatur dicta. Recusandae animi saepe odit maiores quae error ratione ducimus? Distinctio sapiente a commodi iusto numquam praesentium non corporis eaque voluptas quibusdam, asperiores iste eveniet dolore veritatis repellendus debitis quasi veniam sunt sint voluptate fugit cumque architecto perspiciatis. Nihil.</p>
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
