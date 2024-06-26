<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ops! Qualcosa � andato storto.</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
                            background-image: linear-gradient(-225deg, #FFAD83 0%, #82334D 100%);
    	background-image: linear-gradient(to top, #FFAD83 0%, #82334D 100%);
    	    background-attachment: fixed;
    background-repeat: no-repeat;
        }
        h1 {
            color: #FFAD83;
        }
        p {
            font-size: 18px;
            color: black;
        }
        .button {
            display: inline-block;
            padding: 10px 20px;
            font-size: 18px;
            margin-top: 20px;
            color: #fff;
            background-color: #82334D;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }
        .button:hover {
            background-color: #FFAD83;
        }
    </style>
</head>
<body>
	<h1>Opsss! Qualcosa � andato storto.</h1>
	<h2><img src="images/logode.png" alt="Icona di accesso" class="logo"></h2>
    <p>Si � verificato un errore imprevisto, non abbiamo trovato quello che stai cercando. Ci scusiamo per il disagio.</p>
    <a href="home.jsp" class="button">Torna alla Home</a>
</body>
</html>
