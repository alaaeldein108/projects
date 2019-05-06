<?php
   $dbhost = 'localhost';
   $dbuser = 'root';
   $dbpass = '123456789';
   $conn = mysql_connect($dbhost, $dbuser, $dbpass);
   
   if(! $conn ) {
      die('Could not connect: ' . mysql_error());
   }
 echo
 '
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>ASA High School</title>
        <link rel="stylesheet" type="text/css" href="Selection.css">
        <script>/*alert("Welcome to ASA School")*/</script>
    </head>
    <body>
	<form action="update.php" method="post">
        <table>
           <tr> 
           <td><label class="name">Enter new password</label></td>
             <td><input type="text" name="Password" placeholder="New Password" /></td>
            </tr><br>
             </table>
             <input type="submit" value="Update"/>
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