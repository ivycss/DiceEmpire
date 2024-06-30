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
 
   <div class="con">

   <header class="head-form">
      <h2><img src="images/logode.png" alt="Icona di accesso" class="logo"></h2>

   </header>

   <br>
        <p class="titolo">Inserisci i campi richiesti</p>
   <div class="field-set">
     


       
         <input class="form-input" id="txt-input" type="text" placeholder="Email" name="mail" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" required>
     
      <br>
     
        
     

      <!--   Password Input-->
      <input class="form-input" type="password" placeholder="Password" id="pwd"  name="password" required>
     

     
     
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


   </div>
  

  </div>
  <div class="other">
<!--     Sign Up button -->
      <button class="sign-up" type="submit">Registrati</button>
      

   </div>

</form>
</div>

</body>
</html>
