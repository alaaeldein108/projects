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
$ch=$_SESSION["TID"];
$sql = "SELECT TID, Fname, Lname,visits FROM teacher WHERE TID=$ch";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
       $_SESSION["Fname"]=$row["Fname"];
	   $_SESSION["Lname"]=$row["Lname"];
	   $_SESSION["visits"]=$row["visits"];
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
     <form name="Rigster"class="formstyle" >
         <fieldset >
            <legend>Personal Details</legend>   
             <label class="name">Name</label>
             <textarea rows=1 cols="25" readonly><?php echo $_SESSION["Fname"]." ".$_SESSION["Lname"] ?> </textarea>
            <label class="name">ID</label>
            <textarea rows=1 cols="25" readonly> <?php echo $_SESSION["TID"] ?></textarea>
            <label class="name">visits</label>
            <textarea rows=1 cols="4"readonly> <?php echo $_SESSION["visits"] ?></textarea>
             </fieldset>
            </form>
             <div id="avmenu">
      <h2 class="hide">Site menu:</h2>
      <ul>
        <li><a class="current" >First page</a></li>
          <ul>
            <li><a href="Selection.php">Update</a></li>
				<li><a href="deletation.php">Drop</a></li>
            <li><a href="log out.php">Log Out</a></li>
          </ul>
        </li>
      </ul>

    </body>
  </html>	