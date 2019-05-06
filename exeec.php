<?php
#for login
require_once "connection.php";
class exeec extends connection
{
	function run(){
	parent::connect();
   $id=$_POST['ID'];
	$password=$_POST['password'];
	$_SESSION["favcolor"] = "green";
	
	mysql_select_db("asa school");

	$result=mysql_query("select * from student where STID=$id and Password=$password");

if ($result && mysql_num_rows($result) > 0)

    {
		session_start();
		$_SESSION['STID']=$id;
		include 'student page.php';
    }
else
    {
    echo ' <script type="text/javascript"> alert("Not Found");</script>';
	include 'loginS.php';
	}
	}
}
$obj=new exeec();
$obj->run();
?>