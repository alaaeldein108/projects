<?php
 echo'
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>ASA HighSchool</title>
    <link rel="stylesheet" type="text/css" href="login2.css">
    <script>
    /*alert("Welcome to ASA School")*/
    </script>
</head>
<body>
  <form action="exeecT.php"method="post">
    <div id="login-box">
  <div class="left">
    <h1><b>Login In</b></h1>
    <input type="text" name="ID" placeholder="ID"/>
    <input type="password" name="password" placeholder="Password" />
    <input type="submit" name="Login In" value="Login In" />
  </div>
  </div>
</form>
</body>
</html>
';
?>