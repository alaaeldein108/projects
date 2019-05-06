<?php
$servername = "localhost";
$username = "root";
$password = "123456789";
$dbname = "asa school";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
session_start();
$ch=$_SESSION["STID"];
$sql = "SELECT STID, Fname, Lname ,Major,Minor,GPA,Level FROM student WHERE STID=$ch";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
       $_SESSION["Fname"]=$row["Fname"];
	   $_SESSION["Lname"]=$row["Lname"];
	   $_SESSION["Major"]=$row["Major"];
	   $_SESSION["Minor"]=$row["Minor"];
	   $_SESSION["GPA"]=$row["GPA"];
	   $_SESSION["Level"]=$row["Level"];
    }
} else {
    echo "0 results";
}
?>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>ASA High School</title>
        <link rel="stylesheet" type="text/css" href="student page.css">
        <script>/*alert("Welcome to ASA School")*/</script>
    </head>
    <body>
     <form name="Rigster" >
         <fieldset >
            <legend class="name"><b>Personal Details</b></legend> 
            <table>
            <tr>  
            <td><label class="name">Name</label></td>
             <td><textarea rows=1 cols="25" readonly><?php echo $_SESSION["Fname"]." ".$_SESSION["Lname"];?></textarea></td>
             <td><label class="name">major</label></td>
             <td><textarea rows=1 cols="15" readonly><?php echo $_SESSION["Major"];?></textarea></td>
             <td><label class="name">Gpa</label></td>
             <td><textarea rows=1 cols="4" readonly><?php echo $_SESSION["GPA"];?></textarea></td>
             </tr>
             <tr>
            <td><label class="name">ID</label></td>
            <td><textarea rows=1 cols="25"readonly ><?php echo $_SESSION["STID"];?></textarea></td>
            <td><label class="name">Minor</label></td>
            <td><textarea rows=1 cols="15"readonly ><?php echo $_SESSION["Minor"];?></textarea></td>
            <td><label class="name">level</label></td>
             <td><textarea rows=1 cols="4" readonly><?php echo $_SESSION["Level"];?></textarea></td>
            </tr>
            </table>
             </fieldset>
            </form>
             <div id="avmenu">
      <h2 class="hide"><b>Site menu:</b></h2>
      <ul>
        <li><a class="current" >Formal Forms</a></li>
          <ul>
            <li><a href="first.php">Results</a></li>
            <li><a href="log out.php">Log Out</a></li>
          </ul>
        </li>
      </ul>

    </body>
  </html>