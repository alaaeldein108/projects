<?php
require_once "connection.php";
class first extends connection{
	function run(){
		$servername = "localhost";
$username = "root";
$password = "123456789";
$dbname = "asa school";
		$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
	session_start();
	$ch=$_SESSION["STID"];
	$sql = "SELECT CTitle3,CTitle2,CTitle1,Absent1,Absent2,Absent3,Grade1,Grade2,Grade3 FROM course WHERE STUID=$ch";
	$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
       $_SESSION["CTitle1"]=$row["CTitle1"];
	   $_SESSION["CTitle2"]=$row["CTitle2"];
	   $_SESSION["CTitle3"]=$row["CTitle3"];
	   $_SESSION["Absent1"]=$row["Absent1"];
	   $_SESSION["Absent2"]=$row["Absent2"];
	   $_SESSION["Absent3"]=$row["Absent3"];
	   $_SESSION["Grade1"]=$row["Grade1"];
	   $_SESSION["Grade2"]=$row["Grade2"];
	   $_SESSION["Grade3"]=$row["Grade3"];
    }
	} else {
    echo "0 results";
}}
}
$obj=new first();
$obj->run();
?>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>ASA HighSchool</title>
    <link rel="stylesheet" type="text/css" href="first.css">
    <script>
    /*alert("Welcome to ASA School")*/
    </script>
</head>

<body>
<form>
  <h1>Your Data</h1>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
          <th><b>Course</b></th>
          <th><b>Absent</b></th>
          <th><b>Grade</b></th>
        </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
        <tr>
          <td><?php echo $_SESSION["CTitle1"];?></td>
          <td><?php echo $_SESSION["Absent1"];?></td>
          <td><?php echo $_SESSION["Grade1"];?></td>
	       </tr>
        <tr>
          <td><?php echo $_SESSION["CTitle2"];?></td>
          <td><?php echo $_SESSION["Absent2"];?></td>
          <td><?php echo $_SESSION["Grade1"];?></td>
        </tr>
        <tr>
           <td><?php echo $_SESSION["CTitle3"];?></td>
          <td><?php echo $_SESSION["Absent3"];?></td>
          <td><?php echo $_SESSION["Grade3"];?></td>
        </tr>
      </tbody>
    </table>
  </div>
</section>
</div>
</form>
<div id="avmenu">
      <h2 class="hide"><b>Site menu:</b></h2>
      <ul>
        <li><a class="current" >Formal Forms</a></li>
          <ul>
            <li><a href="student page.php">Previous</a></li>
          </ul>
        </li>
      </ul>
</body>
</html>