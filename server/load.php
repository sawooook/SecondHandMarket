
<?php



$conn=mysqli_connect('localhost','root','136253','store');

$sql="SELECT rank_id, rank_score , (SELECT Count(*)+1 from rank where rank_score > t.rank_score )
As rankkkkkkk From rank As t order by rankkkkkkk limit 0, 5";







// $sql = "SELECT * FROM rank order by rank_score desc limit 10";
// echo $sql;
// echo $sql;
// var_dump($sql);
$result = mysqli_query($conn,$sql);
$num = mysqli_num_rows($result);

for($i=0; $i<$num; $i++){

$row = mysqli_fetch_array($result,MYSQLI_ASSOC);
  echo $row['rankkkkkkk'].";". $row['rank_id'].";".$row['rank_score'].";";

}




 ?>
