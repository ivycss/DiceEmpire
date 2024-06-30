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
       
        .total-order {
            text-align: center;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <%@ include file="/navbar.jsp" %>
    <div class="container">
    <%    int idOrdine = Integer.parseInt(request.getParameter("id"));  
   		 Utente user = (Utente) session.getAttribute("auth");%>

        <h1>Dettagli Ordine: <%= idOrdine %></h1>
        <%

            OrdineDettagliModelMD ordineDettagliModel = new OrdineDettagliModelMD();
        	OrdineModelMD ordineModel = new OrdineModelMD();
            Ordine ordine = null;
            Ordine ordine1 = null;
            try {
                ordine = ordineDettagliModel.doRetrieveByKey(idOrdine);
                ordine1 = ordineModel.doRetrieveByKey(idOrdine);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            
            if (ordine != null) {
            	if(user!=null && user.getId()==ordine1.getIdUtente()){
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
                    
                  
                    <p class="total-order">Totale Ordine: &euro; <%= totaleOrdine %></p>
                                   
                </div>
        <% 
            } else { response.sendRedirect("error.jsp");}
            	
            }
            	else {
            
        %>
                <p>Ordine non trovato.</p>
        <% } %>

    <div align="center">
                        <form action="fattura" method="post">
                        <input type="hidden" name="idOrdine" value="<%= idOrdine %>">
                        <button type="submit" class="custom-button">Scarica Fattura</button>
                    </form>
        <a href="user.jsp" class="custom-button">Torna all'Area Utente</a>
        <a href="userorder.jsp" class="custom-button">Torna agli Ordini</a>
    </div>
    </div>
</body>
</html>
