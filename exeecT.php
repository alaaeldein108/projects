<?php
require_once "connection.php";
  class exeect extends connection{
	function run(){
		parent::connect();
	$id=$_POST['ID'];
	$password=$_POST['password'];
	
	mysql_select_db("asa school");

	$result=mysql_query("select * from teacher where TID=$id and Password=$password");

if ($result && mysql_num_rows($result) > 0)
    {
		session_start();
		$sql = mysql_query("UPDATE teacher SET visits=visits+1 WHERE TID=$id");
		$_SESSION['TID']=$id;
		include 'teacher page.php';
    }
else
    {
    echo ' <script type="text/javascript"> alert("Not Found");</script>';
	include 'loginT.php';
	}
	}
  }
  $obj=new exeect();
$obj->run();
?>