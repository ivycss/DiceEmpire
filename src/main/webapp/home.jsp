<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="diceempire.model.*" %>
<%@ page import="diceempire.control.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.Base64" %>
<%
Collection<?> products = (Collection<?>) request.getAttribute("products");
if (products == null) {
    response.sendRedirect("./prodotti");
    return;
}
Prodotto product = (Prodotto) request.getAttribute("product");
DecimalFormat df = new DecimalFormat("0.00");
int i=0;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dice Empire</title>
    <link rel="stylesheet" type="text/css" href="css/home.css">
</head>
<body>
<%@ include file="/login-signup/alertcarrello.jsp" %>
<%@ include file="navbar.jsp" %>
<%
if (products != null && products.size() != 0) {
    Iterator<?> it = products.iterator();
%>
<div class="container">
    <!-- Offers Section -->
    <div class="offers">
        <a href="dettagli.jsp?id=12">
        <img src="images/bannerdragon.png" alt="Offerta Speciale" class="offer-image"></a>
        <!-- Contenuto delle offerte -->
    </div>
<h2 class="titoloprodotti">In Evidenza</h2>
    <!-- Goods Section -->
    <div class="goods">
        <% while (it.hasNext() && i<4) { 
            Prodotto prodotto = (Prodotto) it.next();
            i++;
        %>
        <div class="item">
            <img src="data:image/jpeg;base64, <%= Base64.getEncoder().encodeToString(prodotto.getImmagine()) %>" alt="Merce 1" class="item-image">
            <h3><%= prodotto.getNome() %></h3>
            <p><%= prodotto.getDescCorta() %></p>
            <p class="price">€<%= prodotto.getPrezzoIVA() %></p>	
            <form action="add-to-cart" method="get">  
                            <input type="hidden" name="id" value="<%=prodotto.getId()%>">
                            <button type="submit" class="item-button">Aggiungi al Carrello</button>
                        </form>
            <form action="dettagli.jsp" method="post"> 
                            <input type="hidden" name="id" value="<%=prodotto.getId()%>"> 
                            <button type="submit" class="item-button">Dettagli</button>
                        </form>
        </div>
        <% } %>
    </div>
    <!-- Explore Catalog Button -->
<div class="explore-catalog-button">
    <a href="catalogo.jsp">
    <button class="explore-button">Esplora il Catalogo</button>
</a>

</div>
</div>
<%
} else {
%>
<h2 align="center">Nessun prodotto disponibile</h2>
<%
}
%>
<div class="news">
    <img src="images/news.jpg" alt="News Banner" class="news-banner">

</div>
<jsp:include page="footer.jsp" />
</body>
</html>
