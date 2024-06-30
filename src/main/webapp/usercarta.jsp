<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="diceempire.model.*, diceempire.control.*, java.util.*, diceempire.servlet.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Informazioni Carta</title>
    <link href="${pageContext.request.contextPath}/css/userinfo.css" rel="stylesheet" type="text/css">
</head>
<body>
    <%@ include file="/navbar.jsp" %>
    <div id="main" class="clear">
        <div class="center">
            <%
                Utente user = (Utente) session.getAttribute("auth");
                PagamentoModelMD pagamentoModel = new PagamentoModelMD();

                if (user == null) {
            %>
                    <p class="error">Nessun utente connesso.</p>
            <%
                } else {
                    Carta carta = (Carta) session.getAttribute("carta");
                    if (carta == null) {
                            carta = pagamentoModel.doRetrieveByUser(user.getId());
                            session.setAttribute("carta", carta);
                        }
            %>
                <div class="user-info-form">
                    <h2 class="titolo">Informazioni Carta</h2>
                    <form action="update-carta" method="post">
                        <div class="form-group">
                            <label for="numeroCarta"><strong>Numero Carta (MAX 16 CIFRE):</strong></label>
                            <input type="text" id="numeroCarta" name="numeroCarta" value="<%= carta != null ? carta.getNumeroCarta() : "" %>" pattern="[0-9]{1,16}" required>
                        </div>
                        <div class="form-group">
                            <label for="dataScadenza"><strong>Data Scadenza (ANNO):</strong></label>
                            <input type="text" id="dataScadenza" name="dataScadenza" value="<%= carta != null ? carta.getDataScadenza() : "" %>" pattern="[0-9]{4}" required>
                        </div>
                        <div class="form-group">
                            <label for="cvc"><strong>CVC (3 CIFRE):</strong></label>
                            <input type="text" id="cvc" name="cvc" value="<%= carta != null ? carta.getCvc() : "" %>" pattern="[0-9]{3}" required>
                        </div>
                        <div class="form-group">
                            <label for="nomeInt"><strong>Nome Intestatario:</strong></label>
                            <input type="text" id="nomeInt" name="nomeInt" value="<%= carta != null ? carta.getNomeInt() : "" %>" required>
                        </div>
                        <div class="form-group">
                            <label for="cognomeInt"><strong>Cognome Intestatario:</strong></label>
                            <input type="text" id="cognomeInt" name="cognomeInt" value="<%= carta != null ? carta.getCognomeInt() : "" %>" required>
                        </div>
                        <div class="button">
                            <button type="submit" class="update">Aggiorna Carta</button>
                            <button class="back"><a href="user.jsp">Torna Indietro</a></button>
                        </div>
                    </form>
                    <form action="elimina-carta" method="post">
                        <div class="button">
                            <button type="submit" class="delete">Elimina Carta</button>
                        </div>
                    </form>
                    <%
                        if (session.getAttribute("updateSuccess") != null) {
                    %>
                        <p class="success"><%= session.getAttribute("updateSuccess") %></p>
                    <%
                        session.removeAttribute("updateSuccess");
                        } else if (session.getAttribute("updateError") != null) {
                    %>
                        <p class="error"><%= session.getAttribute("updateError") %></p>
                    <%
                        session.removeAttribute("updateError");
                        }
                    %>
                </div>
            <%
                }
            %>
        </div>
    </div>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</body>
</html>
