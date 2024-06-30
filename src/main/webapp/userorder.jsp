<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="diceempire.model.*" %>
<%@ page import="diceempire.control.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/userorder.css">
    <title>Riepilogo Ordini</title>
</head>
<body>
    <%@ include file="/navbar.jsp" %>
    <div class="container">
        <h1>Riepilogo dei tuoi ordini</h1>

        <table border="1">
            <thead>
                <tr>
                    <th>ID Ordine</th>
                    <th>Data Ordine</th>
                    <th>Dettagli Ordine</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    Utente user = (Utente) session.getAttribute("auth");
                    if (user == null) {
                %>
                    <tr>
                        <td colspan="3">Nessun utente connesso.</td>
                    </tr>
                <% } else { 
                    OrdineModelMD ordineModel = new OrdineModelMD();
                    List<Ordine> ordini = new ArrayList<>();
                    try {
                        ordini = ordineModel.doRetrieveAllByUser(user.getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    
                    for (Ordine ordine : ordini) {
                %>
                    <tr>
                        <td><%= ordine.getIdOrdine() %></td>
                        <td><%= ordine.getDataOrdine() %></td>
                        <td><a href="dettagliordine.jsp?id=<%= ordine.getIdOrdine() %>">
                            <button class="custom-button">Dettagli</button>
                        </a></td>
                    </tr>
                <% 
                    } 
                } 
                %>
            </tbody>
        </table>
        <div align="center">
        <a href="user.jsp" class="back-button">Torna all'Area Utente</a>
    </div>
    </div>
</body>
</html>
