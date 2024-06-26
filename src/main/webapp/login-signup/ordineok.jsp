<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="diceempire.model.*, java.util.*, java.text.DecimalFormat"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ordine Completato</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
            background-image: linear-gradient(-225deg, #FFAD83 0%, #82334D 100%);
            background-image: linear-gradient(to top, #FFAD83 0%, #82334D 100%);
            background-attachment: fixed;
            background-repeat: no-repeat;
        }
        h1 {
            color: #FFAD83;
        }
        p {
            font-size: 18px;
            color: black;
        }
        .button {
            display: inline-block;
            padding: 10px 20px;
            font-size: 18px;
            margin-top: 20px;
            color: #fff;
            background-color: #82334D;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            transition: background-color 0.3s ease;
            margin: 5px;
        }
        .button:hover {
            background-color: #FFAD83;
        }
        .order-summary-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 30px;
        }
        .order-summary {
            background-color: white;
            border-radius: 10px;
            padding: 20px;
            text-align: left;
            color: black;
            max-width: 600px;
            width: 100%;
        }
        .order-summary h2 {
            color: #82334D;
            margin-bottom: 20px;
        }
        .order-summary .item {
            margin-bottom: 10px;
            border-bottom: 1px solid #ddd;
            padding-bottom: 10px;
        }
        .order-summary .item:last-child {
            border-bottom: none;
        }
        .order-summary .item img {
            width: 50px;
            height: auto;
            border-radius: 5px;
            vertical-align: middle;
            margin-right: 10px;
        }
        .order-summary .item-details {
            display: inline-block;
            vertical-align: middle;
        }
        .button-container {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h1>Ordine Completato</h1>
    <p>Grazie mille!! Il tuo ordine è stato correttamente registrato. Puoi continuare ad esplorare il nostro catalogo o tornare alla home.</p>

    <div class="order-summary-container">
        <%
            DecimalFormat df = new DecimalFormat("0.00");
            List<ProdottoInCarrello> prodotti = (List<ProdottoInCarrello>) session.getAttribute("prodottiCheckout");
        %>

        <div class="order-summary">
            <h2>Riepilogo Ordine</h2>
            <% if (prodotti != null && !prodotti.isEmpty()) { %>
                <% for (ProdottoInCarrello c : prodotti) { %>
                    <div class="item">
                        <img src="<%= request.getContextPath() %>/images/<%= c.getItem().getNomeImmagine() %>" alt="<%= c.getItem().getNome() %>">
                        <div class="item-details">
                            <div class="item-name"><%= c.getItem().getNome() %></div>
                            <div class="item-quantity">Quantità: <%= c.getNumItems() %></div>
                            <div class="item-price">Prezzo: &euro; <%= df.format(c.getItem().getPrezzoIVA()) %></div>
                        </div>
                    </div>
                <% } %>
            <% } else { %>
                <p>Non ci sono prodotti nel riepilogo ordine.</p>
            <% } %>
        </div>
        
        <div class="button-container">
            <a href="../catalogo.jsp" class="button">Vai al Catalogo</a>
            <a href="../home.jsp" class="button">Torna alla Home</a>
        </div>
    </div>
</body>
</html>
