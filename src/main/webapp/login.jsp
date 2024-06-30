<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Form</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
<div class="overlay">
   <button class="back"><a href="home.jsp">Torna alla Home</a></button>
<form action="user-login" method="post">
 
   <div class="con">
 
   <header class="head-form">
      <h2><img src="images/logode.png" alt="Icona di accesso" class="logo"></h2>
    
   </header>

   <br>
        <p class="titolo">Accedi o Registrati</p>
   <div class="field-set">
     
 

         <input class="form-input" id="email" type="text" placeholder="Email" name="mail" required>
     
      <br>
     
 
     

      <!--   Password Input-->
      <input class="form-input" type="password" placeholder="Password" id="pwd"  name="password" required>
     

     
     
      <br>



   </div>
  

   


  </div>
  <div class="other">
<!--     Sign Up button -->
      <button class="log-in" type="submit"> Accedi </button>
      <button class="sign-up"><a href="signup.jsp">Registrati</a></button>
      

   </div>

</form>
</div>

</body>
</html>
