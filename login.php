<?php
require_once "connection.php";
class login extends connection
{
	function run(){
	parent::connect();
 echo
 '
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>ASA HighSchool</title>
    <link rel="stylesheet" type="text/css" href="login.css">
    <script>
    /*alert("Welcome to ASA School")*/
    </script>
</head>
<body>
    <div>
    <img class="t"src="teacher.png" alt="Teacher">  
    <img class="s"src="student.png" alt="Student" height=260px>
    <a href="loginT.php"><input class="button1"type="button"value="Log in as Teacher"></a>
    <a href="loginS.php"><input class="button2"type="button" value="Log in as Student"></a>
    </div>
</body>
</html>
';
}
}
$obj=new login();
$obj->run();
?>