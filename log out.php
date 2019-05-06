<?php
class logout{
	function run()
	{
session_start();
session_destroy();
include 'main page.php';
}
}
$obj=new logout();
$obj->run();
?>