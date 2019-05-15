<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>heelop</title>
<style media="screen">

.hello:focus:hover{
background: yellow;
}
.div1 p:nth-child(3){
  color: blue;
}
.div1 p:first-child{
  color: red;
}

.div1 p:last-child{
  color: green;
}


.div1 p:not(:last-child){
  color: green;
}
.div1 p:not(:first-child){
  text-decoration: line-through;
}


</style>

  </head>
  <body>

<div class="div1">
  <p>pppp1</p>
  <p>pppp2</p>
  <p>pppp3</p>
  <p>pppp4</p>
  <p>pppp5</p>
  <p>pppp6</p>


</div>


  </body>
</html>
