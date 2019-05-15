<?php
$conn = mysqli_connect(
  'localhost',
  'root',
  '136253',
  'oo');
$sql = "SELECT * FROM topic";
$result = mysqli_query($conn,$sql);

 ?>

<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <title>WEB</title>
  </head>
  <body>
    <h1><a href="index.php">WEB</a></h1>
<?php $article = array('title'=>'hello','description'=>'hi');?>
    <ol>
      <?php
      $sql = "SELECT * FROM topic";
      $result = mysqli_query($conn,$sql);
      while($row =mysqli_fetch_array($result)){

        echo "<li><a href =\"index.php?id={$row['id']}\">{$row['title']}</a></li>";


      }
      $update_link ='';
      if(isset($_GET['id'])){
      $sql = "SELECT * FROM topic where id={$_GET['id']}";
      $result=mysqli_query($conn,$sql);
      var_dump($result);
      $row=mysqli_fetch_array($result);
      $article['title']=$row['title'];
      $article['description']=$row['description'];


      $update_link = '<a href="update.php?id='.$_GET['id'].'">update</a>';
}
      ?>

    </ol>
    <a href="create.php">create</a>
    <?=$update_link?>
    <h2><?=$article['title']?></h2>
    <?=$article['description']?>
  </body>
</html>
