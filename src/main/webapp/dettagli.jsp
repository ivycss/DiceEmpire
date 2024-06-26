<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import = "diceempire.model.*" %>
<%@page import = "diceempire.control.*" %>

<%
    int productId = Integer.parseInt(request.getParameter("id"));
    ProdottoModelMD model = new ProdottoModelMD();
    Prodotto prodotto = model.doRetrieveByKey(productId);
    if (prodotto != null) { 
%>
<%@include file="/navbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/dettagli.css">
    <title>Dettagli Prodotto</title>
</head>
<body>
    
<div class="product-details">
    <div class="product-image">
      <img src="<%= request.getContextPath() %>/images/<%= prodotto.getNomeImmagine() %>" alt="<%= prodotto.getNome() %>" class="product-image">
    </div>
    <div class="product-divider"></div>
    <h2 class="product-title"><%= prodotto.getNome() %></h2>    
    <div class="product-info">
        <table class="product-info-table">
        
                    <tr>
                <th>Tipo Prodotto</th>
                <td><%= prodotto.getTipoProdotto() %></td>
            </tr>   
			<tr>
                <th>Descrizione</th>
                <td><%= prodotto.getDescLunga() %></td>
            </tr>
            			<tr>
                <th>Età Minima</th>
                <td><%= prodotto.getEta() %></td>
            </tr>
            			<tr>
                <th>Edizione Numero</th>
                <td><%= prodotto.getEdizione() %></td>
            </tr>
            			<tr>
                <th>Edizione Limitata?</th>
                <td><%= prodotto.getEdizioneLimitata() %></td>
            </tr>
                      <tr>
                <th>Prezzo</th>
                <td><strong>€</strong> <%= prodotto.getPrezzoIVA() %> IVA Inclusa </td>
            </tr>
            
            <!-- Aggiungi altre caratteristiche del prodotto qui -->
        </table>
    </div>
    <form action="add-to-cart" method="get">  
                            <input type="hidden" name="id" value="<%=prodotto.getId()%>">
                            <button type="submit" class="button">Aggiungi al Carrello</button>
                        </form>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
<%
    } else {
        out.println("Product not found.");
    }
%>