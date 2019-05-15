<?php
session_start();

//shopping_cart 세션 배열에 존재하는 배열들을 $values 에 넣는다.
foreach($_SESSION["shopping_cart1"] as $keys => $values)
{
//배열의 item_id 값이 클릭한 id 값과 같으면
if($values["item_id"] == $_GET["id"])
{
//세션에서 제거한다.
unset($_SESSION["shopping_cart1"][$keys]);
echo '<script>alert("삭제 되었습니다")</script>';
echo '<script>window.location="product_cart.php"</script>';
}
}

?>
