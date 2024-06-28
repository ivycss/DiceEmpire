<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="diceempire.model.*" %>
<%@ page import="diceempire.control.*" %>
<%@ page import="java.sql.SQLException" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/dettagliordine.css">
    <title>Dettagli Ordine</title>
    <style>
        /* Stili aggiuntivi inline per centrare e rendere in grassetto il testo "Totale Ordine" */
        .total-order {
            text-align: center;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <%@ include file="/navbar.jsp" %>
    <div class="container">
    <%    int idOrdine = Integer.parseInt(request.getParameter("id"));  %>

        <h1>Dettagli Ordine: <%= idOrdine %></h1>
        <%

            OrdineDettagliModelMD ordineDettagliModel = new OrdineDettagliModelMD();
            Ordine ordine = null;
            try {
                ordine = ordineDettagliModel.doRetrieveByKey(idOrdine);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            
            if (ordine != null) {
            	Double totaleOrdine=0.0;
                List<ProdottoInCarrello> prodottiOrdine = ordine.getProdottiOrdine();
        %>
                <div class="order-details">
                    <div class="products-list">
                        <h3>Prodotti</h3>
                        <ul>
                            <% 
                                // Itera attraverso i prodotti dell'ordine e mostra i dettagli
                                for (ProdottoInCarrello prodotto : prodottiOrdine) {
                                	totaleOrdine=totaleOrdine + (prodotto.getPrezzo() * prodotto.getNumItems());
                            %>
                                <li>
                                    <div class="product">
                                        <div class="product-image">
                                            <img src="data:image/jpeg;base64, <%= Base64.getEncoder().encodeToString(prodotto.getImmagine()) %>">
                                        </div>
                                        <div class="product-details">
                                            <h4><%= prodotto.getNome() %></h4>
                                            <p>Quantità: <%= prodotto.getNumItems() %></p>
                                            <p>Prezzo Unitario: &euro; <%= prodotto.getPrezzo() %></p>
                                            <p>Totale: &euro; <%= prodotto.getPrezzo() * prodotto.getNumItems() %></p>
                                            <p>Descrizione: <%= prodotto.getDescCorta() %></p>
                                        </div>
                                    </div>
                                </li>
                            <% } %>
                        </ul>
                    </div>
                    
                    <!-- Totale Ordine centrato e in grassetto -->
                    <p class="total-order">Totale Ordine: &euro; <%= totaleOrdine %></p>
                    
                </div>
        <% 
            } else {
        %>
                <p>Ordine non trovato.</p>
        <% } %>

    <div align="center">
        <a href="user.jsp" class="custom-button">Torna all'Area Utente</a>
        <a href="userorder.jsp" class="custom-button">Torna agli Ordini</a>
    </div>
    </div>
</body>
</html>
