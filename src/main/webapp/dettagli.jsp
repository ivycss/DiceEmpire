<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import = "diceempire.model.*" %>
<%@page import = "diceempire.control.*" %>
    <%@ page import="java.util.*" %>

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
 <%@ include file="/login-signup/alertcarrello.jsp" %>
<div class="product-details">
    <div class="product-image">
      <img src="data:image/jpeg;base64, <%= Base64.getEncoder().encodeToString(prodotto.getImmagine()) %>" alt="<%= prodotto.getNome() %>" class="product-image">
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