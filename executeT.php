<?php
require_once "connection.php";
  class executet extends connection{
	  function run(){
		  $dbhost = 'localhost';
   $dbuser = 'root';
   $dbpass = '123456789';
   $conn = mysql_connect($dbhost, $dbuser, $dbpass);
   
   if(! $conn ) {
      die('Could not connect: ' . mysql_error());
   }
   $id=$_POST['ID'];
	$finame=$_POST['firstname'];
	$laname=$_POST['lastname'];
	$email=$_POST['email'];
	$password=$_POST['password'];
	$gender=$_POST['gender'];
	mysql_select_db('asa school');
   $result=mysql_query("select * from teacher where TID=$id");

if ($result && mysql_num_rows($result) > 0)
{
	  echo ' <script type="text/javascript"> alert("Already Existing");</script>';
	include 'sign upT.php';
    }
else
    {
		$sql = "INSERT INTO  teacher "."(TID,Fname,Lname,Email,Password,Gender)" ."VALUES ('$id','$finame','$laname','$email','$password','$gender')";
	$retval = mysql_query( $sql, $conn );
   
	if(! $retval ) {
      die('Could not enter data: ' . mysql_error());
	}
	echo "Entered data successfully\n";
	mysql_close($conn);
	include 'main page.php';
  
	  }
	  }
  }
  $obj=new executet();
$obj->run();
?>