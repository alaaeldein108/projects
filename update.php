<?php
 $dbhost = 'localhost';
   $dbuser = 'root';
   $dbpass = '123456789';
   $conn = mysql_connect($dbhost, $dbuser, $dbpass);
   
   if(! $conn ) {
      die('Could not connect: ' . mysql_error());
   }
   session_start();
   mysql_select_db("asa school");
   $password=$_POST['Password'];
   $id=$_SESSION['TID'];
   $sql = mysql_query("UPDATE teacher SET Password=+$password WHERE TID=$id");
   echo ' <script type="text/javascript"> alert("Update Sucess");</script>';
include 'Selection.php';
?>