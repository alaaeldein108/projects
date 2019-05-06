<?php
	# for sign up
	require_once "connection.php";
	class drop extends connection{
		function run(){
$dbhost = 'localhost';
   $dbuser = 'root';
   $dbpass = '123456789';
   $conn = mysql_connect($dbhost, $dbuser, $dbpass);
   
   if(! $conn ) {
      die('Could not connect: ' . mysql_error());
   }
	$id=$_POST['DROP'];
	mysql_select_db('asa school');
	$result=mysql_query("select * from student where STID=$id");
	if ($result && mysql_num_rows($result) >0)
	{
		$mysql_statement1 = mysql_query("DELETE FROM student WHERE STID=$id");
		$mysql_statement2 = mysql_query("DELETE FROM course WHERE STUID=$id");
		echo ' <script type="text/javascript"> alert("DELETE Sucess");</script>';
		include 'deletation.php';
	}
	else
	{
		echo ' <script type="text/javascript"> alert("Not Found");</script>';
		include 'deletation.php';
	}
	
	}
	}
	$obj=new drop();
	$obj->run();
?>