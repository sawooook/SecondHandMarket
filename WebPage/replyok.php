<?php
$conn = mysqli_connect("localhost","root","136253","hihi");
session_start();
error_reporting(E_ALL);
ini_set('display_errors',1);

$idch = $_GET['id'];

if($_POST['content']==""){
  echo "<script>alert('글을입력해주세요');</script>";
}else{
$idch1 = $_POST['content'];

	$sql = "insert into reply(con_num,name,content) values('".$idch."','".$_SESSION['userid']."','{$_POST['content']}')";

	$result=mysqli_query($conn,$sql);
}
?>
	<h3>댓글목록</h3>
	<?php
	$sql2 = "SELECT * FROM reply where con_num = '".$idch."' order by ind asc";
	$result=mysqli_query($conn,$sql2);
	while($row=mysqli_fetch_array($result)){
		?>

		<div class="dap_lo">
						<div><b><?php echo $row['name'];?></b></div>
						<div class="dap_to"><?php echo nl2br("$row[content]"); ?></div>
						<div class="rep_me dap_to"><?php echo $row['date']; ?></div>
						<div class="rep_me">
              <a href="reply_update.php?id=<?= $row['ind']?>">수정</a>
              <a href="reply_delete.php?id=<?= $row['ind']?>">삭제</a>
						</div>
					</div>
					<?php } ?>
				<div class="dap_ins">
					<ul>
						<li class="fl"><textarea name="content" class="reply_content" id="re_content" cols="80" rows="3"></textarea></li>
						<li><input type="button" id="rep_bt" value="댓글" class="re_bt" onclick="reply_ok();" /></li>
					</ul>
				</div>
