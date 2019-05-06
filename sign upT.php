<?php
 echo'
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>ASA HighSchool</title>
        <link rel="stylesheet" type="text/css" href="sign up.css">
        <script >
             function ValidateEmail(inputText)
            {
                var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
                if(inputText.value.match(mailformat))
                {
                    document.form.email.focus();
                    return true;
                }
                else
                {
                    alert("You have entered an invalid email address!");
                    document.form.email.focus();
                    return false;
                } 
          }    
           function CheckPassword(inputtxt) 
          { 
              var passw=  /^\w{7,15}$/;
              if(inputtxt.value.match(passw)) 
              { 
                  alert("Correct password")
                  return true;
              }
              else
              { 
                  alert("the password is Wrong...!")
                  return false;
              }
          }
          function confirming() 
          {
                var pass1 = document.form.password.value;
                var pass2 = document.form.confirmpassword.value;
                  if (pass1 != pass2)
                   {
                        alert("Passwords Do not match");
                  }
                  else 
                    {
                        alert("Passwords Match!!!");
                    }
          }
          function allfunction()
          {
              document.form.email.focus();
             document.form.Password.focus();
          }
        </script>
        <style>
        .rigster
        {   
        border: 5px;
        }
        </style>

    </head>
    <body onload="allfunction()">
    <div id="login-box">
    <div class="left">
    <h1><b>Sign up</b></h1>
    <form name="form" action="executeT.php" method="post">
    <input type="text" name="firstname" placeholder="first name" />
    <input type="text" name="lastname" placeholder="last name" />
    <input type="text" name="ID" placeholder="ID"/>
    <input type="text" name="email" placeholder="E-mail" />
    <input type="password" name="password" placeholder="Password" />
    <input type="password" name="confirmpassword" placeholder="confirm password" />
    <input type="text" name="gender" placeholder="Gender" />
    <!----<input type="radio" name="browser"value="Male">Male<br>
     <input type="radio" name="browser"value="Female">Female<br>-->
    <input type="submit" name="Sign" value="Submit" onclick="ValidateEmail(document.form.email);CheckPassword(document.form.password);confirming()"/>
    </form>
  </div>
</body>
</html>
';
?>