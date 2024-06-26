<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="diceempire.model.*, diceempire.control.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Area Utente</title>
    <link href="${pageContext.request.contextPath}/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
    <%@ include file="/navbar.jsp" %> <!-- Includi il tuo file di navigazione se necessario -->
    <div id="main" class="clear">
        <div class="center">
            <div class="welcome-container">
                <% Utente user = (Utente) session.getAttribute("auth"); %>
                <% if (user == null) { %>
                    <p class="error">Nessun utente connesso.</p>
                <% } else { %>
                    <div class="welcome-text">
                        <h1>Benvenuto/a <%= user.getNome() %> nella tua area utente.</h1>
                    </div>
                    <div class="button-container">
                        <button class="user-button" onclick="window.location.href='userinfo.jsp'">Modifica Dati Personali</button>
                        <button class="user-button" onclick="window.location.href='usercarta.jsp'">Modifica Metodo Pagamento</button>
                        <button class="user-button" onclick="window.location.href='userordini.jsp'">Visualizza Ordini</button>
                    </div>
                <% } %>
            </div>
        </div>
    </div>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script> <!-- Font Awesome per icone -->
</body>
</html>