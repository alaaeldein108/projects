<?php
require_once "connection.php";
class mainpage extends connection
{
	function view(){
		parent::connect();
echo
'
<html>
<head>
<title>ASA High School</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="main page.css">
</head>
<body>

<div id="HEADER">
	<h1>ASA HighSChool { صنع في مصر و ليس لدينا فروع اخري}</h1>
	<ul>
		<li><a href="contact.php">Contact Us</a></li>
		<li><a href="sign up.php">Sign Up</a></li>
        <li><a href="login.php">Login In</a></li>
	</ul>
	<div class="Visual"> </div>
</div>

<div id="CONTENT">
	<h2>Welcome</h2>
	<div id="TEXT">
        <p>ASA itself on the quality of its educational programmes, the professionalism of its staff,the enthusiasm of its students and the high level of support provided by parents and community members.</P>
		<P>WE are an open school and we actively seek participation and involvement from the whole school community.The school commenced on the 30th January, 1996 with 6 primary classes and a morning preschool group</P>
		<P>In 2004 the school undertook a comprehensive community survey seeking to clarify the effectiveness of our service delivery and identify any changes that needed to be made. This was undertaken within the parameters of:</P>
		<P> <strong>.</strong>Curriculum, Teaching and Learning<br>
		<strong>.</strong>Environment and Ethos<br>
		<strong>.</strong>Partnerships and Services</p> 
	
	</div>
</div>

<div id="FOOTER">
<p>FAQ &bull; Terms &bull; Privacy Policy &bull; About Us</p>
<p>Your School &copy; 2006 </p>
<p>Designed by ASA TEAM</p>
</div>

</body>
</html>
';
}
}
$obj=new mainpage();
$obj->view();
?>