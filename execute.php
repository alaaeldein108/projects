<?php
require_once "connection.php";
	# for sign up
	class execute extends connection{
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
	$major=$_POST['major'];
	$minor=$_POST['minor'];
	$gpa=$_POST['gpa'];
	$level=$_POST['level'];
	 mysql_select_db('asa school');
	$result=mysql_query("select * from student where STID=$id");
	if ($result && mysql_num_rows($result) >0)
		{
			echo ' <script type="text/javascript"> alert("Already Existing");</script>';
		include 'Sign upS.php';
		}
else
    {
		$sql = "INSERT INTO  student "."(STID,Fname,Lname,Email,Major,Minor,GPA,Password,Gender,Level)" ."VALUES ('$id','$finame','$laname','$email','$major','$minor','$gpa','$password','$gender','$level')";  
		$retval = mysql_query( $sql, $conn );
   
	if(! $retval ) {
      die('Could not enter data: ' . mysql_error());
	}
   $subjects1=array('Arabic','math','science1');
   $subjects2=array('Algebra','Engineering','science2');
   $subjects3=array('Arabic2','math2','science3');
   echo "Entered data successfully\n";
	$a1=rand(0,4);
   $g1=rand(18,100);
   $a2=rand(0,4);
   $g2=rand(18,100);
   $a3=rand(0,4);
   $g3=rand(18,100);
   switch($level)
   {
	   case '1':
	   {
		   $sql ="INSERT INTO  course "."(CTitle3,CTitle2,CTitle1,Absent1,Absent2,Absent3,Grade1,Grade2,Grade3,STUID)" ."VALUES ('$subjects1[0]','$subjects1[1]','$subjects1[2]','$a1','$a2','$a3','$g1','$g2','$g3','$id')";
		   break;
	   }
	   case '2':
	   {
		  $sql ="INSERT INTO  course "."(CTitle3,CTitle2,CTitle1,Absent1,Absent2,Absent3,Grade1,Grade2,Grade3,STUID)" ."VALUES ('$subjects2[0]','$subjects2[1]','$subjects2[2]','$a1','$a2','$a3','$g1','$g2','$g3','$id')";
		   break;
	   }
	   case '3':
	   {
		 $sql ="INSERT INTO  course "."(CTitle3,CTitle2,CTitle1,Absent1,Absent2,Absent3,Grade1,Grade2,Grade3,STUID)" ."VALUES ('$subjects3[0]','$subjects3[1]','$subjects3[2]','$a1','$a2','$a3','$g1','$g2','$g3','$id')";
		   break;
	   }
   }
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
$obj=new execute();
$obj->run();	
   ?>