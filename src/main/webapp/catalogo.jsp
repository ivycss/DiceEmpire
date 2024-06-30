<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="diceempire.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Base64" %>
<%
Collection<?> products = (Collection<?>) request.getAttribute("products");
if (products == null) {
    response.sendRedirect("./catalogo");
    return;
}
Prodotto product = (Prodotto) request.getAttribute("product");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tutti i Prodotti - Dice Empire</title>
    <link rel="stylesheet" type="text/css" href="css/catalogo.css">
</head>
<body>
<%@ include file="/login-signup/alertcarrello.jsp" %>
<%@ include file="navbar.jsp" %>
<%
if (products != null && products.size() != 0) {
    Iterator<?> it = products.iterator();
%>
<div class="container">
    <h2 class="titoloprodotti">Tutti i Prodotti</h2>

    <div class="filter-buttons">
        <form action="catalogo" method="get">
            <button type="submit" name="filter" value="Gioco" class="outlined-button">Giochi </button>
            <button type="submit" name="filter" value="Carte" class="outlined-button">Carte</button>
        </form>
    </div>
    

    <div class="sort-section" style="float: left; margin-bottom: 20px;">
        <form action="catalogo" method="get">
            <label for="sort">Ordina per:</label>
            <select name="sort" id="sort" onchange="this.form.submit()">
                <option value="">Seleziona</option>
                <option value="prezzo">Prezzo</option>
                <option value="nome">Nome</option>
            </select>
        </form>
    </div>
    

    <div class="goods">
<%
while (it.hasNext()) { 
    Prodotto prodotto = (Prodotto) it.next();
%>
        <div class="item">
            <img src="data:image/jpeg;base64, <%= Base64.getEncoder().encodeToString(prodotto.getImmagine()) %>" class="item-image">
            <h3><%= prodotto.getNome() %></h3>
            <p><%= prodotto.getDescCorta() %></p>
            <p class="price">€<%= prodotto.getPrezzoIVA() %></p>
            <form action="add-to-cart" method="get">
                <input type="hidden" name="id" value="<%= prodotto.getId() %>">
                <button type="submit" class="item-button">Aggiungi al Carrello</button>
            </form>
            <form action="dettagli.jsp" method="post">
                <input type="hidden" name="id" value="<%= prodotto.getId() %>">
                <button type="submit" class="item-button">Dettagli</button>
            </form>
        </div>
        <%
            }
        } else {
        %>
        <h2 align="center">Nessun prodotto disponibile</h2>
        <%
        }
        %>
    </div>
</div>
<div class="news">
    <img src="images/news.jpg" alt="News Banner" class="news-banner">
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
