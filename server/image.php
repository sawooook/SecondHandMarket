
<?php
if(isset($_REQUEST['name'])){

  $gg=$_REQUEST['name'];
}


$target_path = basename( $_FILES['uploadedfile']['name']);

if(move_uploaded_file($_FILES['uploadedfile']['tmp_name'], $target_path)) {
echo "$gg@$target_path";
} else{
echo "There was an error uploading the file, please try again!";
}
?>
