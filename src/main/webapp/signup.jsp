<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Form</title>
<link rel="stylesheet" type="text/css" href="css/signup.css">
</head>
<body>
<div class="overlay">
   <button class="back"><a href="home.jsp">Torna alla Home</a></button>
<form action="user-signup" method="post">
   <!--   con = Container  for items in the form-->
   <div class="con">
   <!--     Start  header Content  -->
   <header class="head-form">
      <h2><img src="images/logode.png" alt="Icona di accesso" class="logo"></h2>
      <!--     A welcome message or an explanation of the login form -->
   </header>
   <!--     End  header Content  -->
   <br>
        <p class="titolo">Inserisci i campi richiesti</p>
   <div class="field-set">
     
      <!--   user name -->

        <!--   user name Input-->
         <input class="form-input" id="txt-input" type="text" placeholder="Email" name="mail" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" required>
     
      <br>
     
           <!--   Password -->
     

      <!--   Password Input-->
      <input class="form-input" type="password" placeholder="Password" id="pwd"  name="password" required>
     
<!--      Show/hide password  -->
     
     
      <br>
      <div class="form-input">
         <input class="form-input" type="text" placeholder="Nome" name="nome" required>
      </div>
      <!-- Cognome -->
      <div class="form-input">
         <input class="form-input" type="text" placeholder="Cognome" name="cognome" required>
      </div>
      <!-- Età -->
      <div class="form-input">
         <input class="form-input" type="text" placeholder="Età" name="eta" required>
      </div>
            <!-- Codice Fiscale -->
      <div class="form-input">
         <input class="form-input" type="text" placeholder="Codice Fiscale" name="cf" required>
      </div>
      <!-- Numero di Telefono -->
      <div class="form-input">
         <input class="form-input" type="text" placeholder="Numero di Telefono (9)" name="numeroTelefono" pattern="[0-9]{6,9}" required>
      </div>
      <!-- Città -->
      <div class="form-input">
         <input class="form-input" type="text" placeholder="Città" name="citta" required>
      </div>
      <!-- Via -->
      <div class="form-input">
         <input class="form-input" type="text" placeholder="Via" name="via" required>
      </div>
      <!-- CAP -->
      <div class="form-input">
         <input class="form-input" type="text" placeholder="CAP" name="cap" required>
      </div>
   </div>
<!--        buttons -->
<!--      button LogIn -->

   </div>
  
<!--   other buttons -->
   

<!--   End Conrainer  -->
  </div>
  <div class="other">
<!--     Sign Up button -->
      <button class="sign-up" type="submit">Registrati</button>
      
<!--      End Other the Division -->
   </div>
  <!-- End Form -->
</form>
</div>

</body>
</html>
