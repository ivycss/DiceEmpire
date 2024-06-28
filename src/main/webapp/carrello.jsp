<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="diceempire.model.*, java.util.*, java.text.DecimalFormat" %>
<%@ page import="java.util.Base64" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/carrello.css">
    <script src="js/carrello.js"></script>
    <title>Il tuo Carrello</title>
</head>
<body>
    <%@ include file="/navbar.jsp" %>

    <%
        DecimalFormat df = new DecimalFormat("0.00");
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        List<ProdottoInCarrello> prodotti = cart.getProdottiCarrello();
    %>
    <div class="card">
        <div class="row">
            <div class="col-md-8 cart">
                <div class="title">
                    <div class="row">
                        <div class="col"><h4><b>Benvenuto nel tuo Carrello</b></h4></div>
                        <% if (prodotti != null && !prodotti.isEmpty()) { %>
                        <% int i = 0; %>
                        <% for (ProdottoInCarrello c : prodotti) { i++; %>
                        <div class="row border-top border-bottom">
                            <div class="row main align-items-center">
                                <div class="col-2"><img class="img-fluid" src="data:image/jpeg;base64, <%= Base64.getEncoder().encodeToString(c.getItem().getImmagine()) %>"></div>
                                <div class="col">
                                    <div class="row text-muted"><%= c.getItem().getNome() %></div>
                                    <div class="row"><%= c.getItem().getDescCorta() %></div>
                                </div>
                                <div class="col">
                                    <a href="update-cart?id=<%= c.getItem().getId() %>&action=decrement">-</a>
                                    <span class="border"><%= c.getNumItems() %></span>
                                    <a href="update-cart?id=<%= c.getItem().getId() %>&action=increment">+</a>
                                </div>
                                <div class="col">&euro; <%= df.format(c.getItem().getPrezzoIVA()) %>

                                    <form action="remove-from-cart" method="get">
                                        <input type="hidden" name="id" value="<%= c.getItem().getId() %>">
                                        <button type="submit" class="custom-button">Rimuovi</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <% } %>
                        <div class="back-to-shop"><a href="home.jsp">&leftarrow;</a><span class="text-muted">Ritorna al Catalogo</span></div>
                    </div>
                    <div class="col-md-4 summary">
                        <div><h5><b>Riepilogo</b></h5></div>
                        <hr>
                        <div class="row">
                            <div class="col" style="padding-left:0;">Prodotti nel carrello: <%= i %></div>
                            <div class="col text-right">&euro; <%= df.format(cart.getTotalPrice()) %></div>
                        </div>
                        <form>
                            <p>Modalità di Spedizione</p>
                            <select id="spedizione" onchange="aggiornaTotale()">
                                <option value="5,00" class="text-muted" selected>Spedizione Economica &euro;5,00</option>
                                <option value="10,00" class="text-muted">Spedizione Rapida &euro;10,00</option>
                            </select>
                        </form>
                        <div class="row" style="border-top: 1px solid rgba(0,0,0,.1); padding: 2vh 0;">
                            <div class="col">Prezzo Totale:</div>
                            <input type="hidden" id="totalPrice" value="<%= cart.getTotalPrice() %>">
                            <p id="totale">&euro; <%= cart.getTotalPrice() %></p>
                        </div>
                            <form action="checkout" method="get">
                            <button type="submit" class="custom-button">Procedi al Checkout</button>
                        </form>
                    </div>
                </div>
                <% } else { %>
                <div class="row text-muted text-center"><h4>Il tuo carrello è vuoto.</h4></div>
                <% } %>
            </div>
        </div>
    </div>
</body>
</html>
