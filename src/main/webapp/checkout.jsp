<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="diceempire.model.*, java.util.*, java.text.DecimalFormat, diceempire.control.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/checkout.css">
    <title>Checkout</title>
</head>
<body>
    <%@ include file="/navbar.jsp" %>

    <%
        DecimalFormat df = new DecimalFormat("0.00");
        List<ProdottoInCarrello> prodotti = (List<ProdottoInCarrello>) session.getAttribute("prodottiCheckout");
        Utente user = (Utente) session.getAttribute("auth");
        PagamentoModelMD pagamentoModel = new PagamentoModelMD();
        Carta carta = pagamentoModel.doRetrieveByUser(user.getId());
    %>

    <div class="card">
        <div class="title">
            <h4><b>Ancora un passo ed è fatta!</b></h4>
        </div>

        <% if (prodotti != null && !prodotti.isEmpty()) { %>
            <div class="cart-items">
                <% for (ProdottoInCarrello c : prodotti) { %>
                    <div class="cart-item">
                        <img class="product-image" src="<%= request.getContextPath() %>/images/<%= c.getItem().getNomeImmagine() %>" alt="<%= c.getItem().getNome() %>">
                        <div class="item-details">
                            <div class="item-name"><%= c.getItem().getNome() %></div>
                            <div class="item-description"><%= c.getItem().getDescCorta() %></div>
                            <div class="item-quantity">Quantità: <%= c.getNumItems() %></div>
                            <div class="item-price">&euro; <%= df.format(c.getItem().getPrezzoIVA()) %></div>
                        </div>
                    </div>
                <% } %>
            </div>

            <div class="payment-info">
                <form action="checkout" method="post">
                    <% if (carta != null) { %>
                        <h2>Metodo di Pagamento Presente</h2>
                        <div class="existing-card">
                            <p>Numero Carta: <%= carta.getNumeroCarta() %></p>
                            <p>Intestatario: <%= carta.getNomeInt() %> <%= carta.getCognomeInt() %></p>
                            <p>Data di Scadenza: <%= carta.getDataScadenza() %></p>
                        </div>
                        <div class="button-container">
                            <button type="button" class="custom-button" onclick="location.href='usercarta.jsp';">Modifica Carta</button>
                            <button type="submit" class="custom-button">Procedi con il Pagamento</button>
                        </div>
                    <% } else { %>
                        <h2>Inserisci una carta valida per il pagamento</h2>
                        <div class="card-inputs">
                            <label>Numero Carta (MAX 16 CIFRE): 
                                <input type="text" name="numeroCarta" pattern="[0-9]{1,16}" required>
                            </label>
                            <label>Nome Intestatario: 
                                <input type="text" name="nomeInt" required>
                            </label>
                            <label>Cognome Intestatario: 
                                <input type="text" name="cognomeInt" required>
                            </label>
                            <label>CVC: (3 CIFRE)
                                <input type="text" name="cvc" pattern="[0-9]{3}" required>
                            </label>
                            <label>Data di Scadenza (ANNO): 
                                <input type="text" name="dataScadenza" pattern="[0-9]{4}" required>
                            </label>
                        </div>
                        <div class="button-container">
                            <button type="submit" class="custom-button">Procedi con il Pagamento</button>
                        </div>
                    <% } %>
                </form>
            </div>
        <% } else { %>
            <div class="empty-cart">
                <h4>Il tuo carrello è vuoto.</h4>
            </div>
        <% } %>
    </div>
</body>
</html>
