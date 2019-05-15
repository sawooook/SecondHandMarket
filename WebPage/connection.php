
<?php

$hi=$_POST["so"];
$saouk=$_POST["soso"];


if($hi==$saouk){

  ?>
  <div style="color:green" class="use">
  이메일 인증 성공
  <input type="hidden" value="1" name="use"/>
  </div>
  <?php
  }else{
  ?>
  <div style="color:red" class="use">
  이메일을 다시확인해주세요
  <input type="hidden" value="0" name="use"/>
  </div>
  <?php
  }

  ?>
