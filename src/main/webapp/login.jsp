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
   <!--   con = Container  for items in the form-->
   <div class="con">
   <!--     Start  header Content  -->
   <header class="head-form">
      <h2><img src="images/logode.png" alt="Icona di accesso" class="logo"></h2>
      <!--     A welcome message or an explanation of the login form -->
   </header>
   <!--     End  header Content  -->
   <br>
        <p class="titolo">Accedi o Registrati</p>
   <div class="field-set">
     
      <!--   email -->
      <!--   email Input-->
         <input class="form-input" id="email" type="text" placeholder="Email" name="mail" required>
     
      <br>
     
           <!--   Password -->
     

      <!--   Password Input-->
      <input class="form-input" type="password" placeholder="Password" id="pwd"  name="password" required>
     
<!--      Show/hide password  -->
     
     
      <br>
<!--        buttons -->
<!--      button LogIn -->

   </div>
  
<!--   other buttons -->
   

<!--   End Conrainer  -->
  </div>
  <div class="other">
<!--     Sign Up button -->
      <button class="log-in" type="submit"> Accedi </button>
      <button class="sign-up"><a href="signup.jsp">Registrati</a></button>
      
<!--      End Other the Division -->
   </div>
  <!-- End Form -->
</form>
</div>

</body>
</html>
