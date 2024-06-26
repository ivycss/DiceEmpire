<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "diceempire.model.*" %>
<%@page import = "diceempire.control.*" %>
<%
int productId = Integer.parseInt(request.getParameter("id"));
ProdottoModelMD model = new ProdottoModelMD();
Prodotto prodotto = model.doRetrieveByKey(productId);
if (prodotto != null) {
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/admincatalogo.css" rel="stylesheet" type="text/css">
    <title>Dettagli Prodotto</title>
</head>
<style>
        body {
            font-family: Arial, sans-serif;
            font-size: 19px;
            line-height: 1.9;
            color: #333;
            margin: 0;
            padding: 0;
            background-image: linear-gradient(-225deg, #FFAD83 0%, #82334D 100%);
            background-image: linear-gradient(to top, #FFAD83 0%, #82334D 100%);
            background-attachment: fixed;
            background-repeat: no-repeat;
        }
        
        table {
            border-collapse: separate;
            border-spacing: 0;
            border-radius: 10px;
            overflow: hidden;
            width: 500px
        }

        td, th {
            padding: 10px;
            width: 30px
        }

        table, th, td {
            text-align: center;
            border: 2.5px solid black;
        }

        .outlined-button {
            border: 2.5px solid #000;
            padding: 12px 28px;
            background-color: #FA706E;
            font-size: 18px;
            cursor: pointer;
            border-radius: 23px;
        }

        .outlined-button:hover {
            color: #fff;
            background-color: #000;
        }
    </style>
<body>
    <h2 align="center">DETTAGLI</h2>
    <table align="center">
        <tr>
            <th>Produttore</th>
            <th>Tipo Prodotto</th>
            <th>Tipo Gioco</th>
            <th>Tipo Carte</th>
            <th>Descrizione Dettagliata</th>
        </tr>
        <tr>
            <td><%=prodotto.getProduttore()%></td>
            <td><%=prodotto.getTipoProdotto()%></td>
            <td><%=prodotto.getTipoGioco()%></td>
            <td><%=prodotto.getTipoCarte()%></td>
            <td><%=prodotto.getDescLunga()%></td>
        </tr>
    </table>
    
    <h2 align="center"></h2>
    <table align="center">
        <tr>
            <th>Prezzo</th>
            <th>Quantità</th>
            <th>Età Minima</th>
            <th>Edizione</th>
            <th>Edizione Limitata</th>
        </tr>
        <tr>
            <td><%=prodotto.getPrezzo()%></td>
            <td><%=prodotto.getQuantita()%></td>
            <td><%=prodotto.getEta()%></td>
            <td><%=prodotto.getEdizione()%></td>
            <td><%=prodotto.getEdizioneLimitata()%></td>
        </tr>
    </table>
        <br>
    <div align="center">
        <a href="catalogoadmin.jsp" class="outlined-button">Torna al Catalogo</a>
    </div>
</body>
<%
}
%>
</html>
