<?php
   $dbhost = 'localhost';
   $dbuser = 'root';
   $dbpass = '123456789';
   $conn = mysql_connect($dbhost, $dbuser, $dbpass);
   
   if(! $conn ) {
      die('Could not connect: ' . mysql_error());
   }
echo'
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>ASA HighSchool</title>
    <link rel="stylesheet" type="text/css" href="deletation.css">
    <script>
    /*alert("Welcome to ASA School")*/
    </script>
</head>
<body>
<form action="drop.php" method="post">
<label> ID of Student for dropping</label></td>
<input type="text" name="DROP" placeholder="DROPPED ID" /> <br><br>
<input type="submit" value="DROP"/>
<div id="avmenu">
      <h2 class="hide"><b>Site menu:</b></h2>
      <ul>
        <li><a class="current" >Formal Forms</a></li>
          <ul>
            <li><a href="teacher page.php">Previous</a></li>
          </ul>
        </li>
      </ul>
	  
</form>
</body>
</html>';
?>