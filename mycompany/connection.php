<?php

$server_name = "localhost";
$user_name = "tatenda";
$password = "kabike";
$db = "company";

$conn = mysqli_connect($server_name, $user_name, $password, $db);

if(!$conn){

  die("Connection Error");
}


 ?>
