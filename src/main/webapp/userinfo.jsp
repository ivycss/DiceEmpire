<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="diceempire.control.*, diceempire.connection.*, diceempire.model.*, java.util.*, java.sql.Connection" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/userinfo.css" rel="stylesheet" type="text/css">
    <title>User Information</title>
</head>
<body>
    <%@ include file="/navbar.jsp" %>
    <div id="main" class="clear">
        <div class="center">
            
            <%
                Utente user = (Utente) session.getAttribute("auth");

                if (user == null) {
            %>
                    <p class="error">Nessun utente connesso.</p>
            <%
                } else {
            %>             
                <div class="user-info-form">
                    <h2 class="titolo">Dati Personali</h2>
                    <form action="user-login" method="post">
                        <input type="hidden" name="action" value="update">
                        <div class="form-group">
                            <label for="cf"><strong>CF:</strong></label>
                            <input type="text" id="cf" name="cf" value="<%= user.getCf() %>" required>
                        </div>
                        <div class="form-group">
                            <label for="nome"><strong>Nome:</strong></label>
                            <input type="text" id="nome" name="nome" value="<%= user.getNome() %>" required>
                        </div>
                        <div class="form-group">
                            <label for="cognome"><strong>Cognome:</strong></label>
                            <input type="text" id="cognome" name="cognome" value="<%= user.getCognome() %>" required>
                        </div>
                        <div class="form-group">
                            <label for="age"><strong>Età:</strong></label>
                            <input type="text" id="age" name="age" value="<%= user.getAge() %>" required>
                        </div>
                        <div class="form-group">
                            <label for="telefono"><strong>Numero Telefono:</strong></label>
                            <input type="text" id="telefono" name="telefono" value="<%= user.getTelefono() %>" required>
                        </div>
                        <div class="form-group">
                            <label for="mail"><strong>Email:</strong></label>
                            <input type="text" id="mail" name="mail" value="<%= user.getMail() %>" required>
                        </div>
                        <div class="form-group">
                            <label for="citta"><strong>Città:</strong></label>
                            <input type="text" id="citta" name="citta" value="<%= user.getCitta() %>" required>
                        </div>
                        <div class="form-group">
                            <label for="cap"><strong>CAP:</strong></label>
                            <input type="text" id="cap" name="cap" value="<%= user.getCap() %>" required>
                        </div>
                        <div class="form-group">
                            <label for="via"><strong>Via:</strong></label>
                            <input type="text" id="via" name="via" value="<%= user.getVia() %>" required>
                        </div>
                        <div class="button">
                            <button type="submit" class="update">Aggiorna Dati</button>
                            <button class="back"><a href="user.jsp">Torna Indietro</a></button>
                        </div>
                    </form>
                </div>
            <%
                }
            %>
        </div>
    </div>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</body>
</html>
