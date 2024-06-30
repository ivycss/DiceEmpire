<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List, diceempire.model.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DICE EMPIRE</title>

    <link rel="stylesheet" href="css/navbar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="js/tastocerca.js"></script>
</head>
<body>
<nav>
    <img src="images/logode.png" alt="Logo del sito" class="logo" style="width: 6.5%;">
     <div id="search-box">
        <input type="text" id="search-input" placeholder="Cerca...">
        <div id="search-results"></div>
    </div>
    <div class="nav-center">
        <ul>
            <li><a href="home.jsp"><i class="fas fa-home"></i></a></li>
           <% String userRole = (String) session.getAttribute("userRole");
           if ("admin".equals(userRole)) { %>
                <li><a href="catalogoadmin.jsp"><i class="fa-solid fa-list" style="color: #ffffff;"></i></a></li>
           <% } else { %>
                <li><a href="catalogo.jsp"><i class="fa-solid fa-list" style="color: #ffffff;"></i></a></li>
           <% } %>
            <li><a class="fa-solid fa-bag-shopping" href="carrello.jsp"><span class="badge badge-danger"></span></a></li>
            <% if ("admin".equals(userRole) || "user".equals(userRole)) { %>
                <li><a href="user.jsp"><i class="fa-regular fa-circle-user"></i></a></li>
            <% } %>
        </ul>
    </div>
        <% if (session.getAttribute("auth") != null) { %>
            <a href="<%= request.getContextPath() %>/Logout"><i class="fa-solid fa-sign-out-alt" style="color: #ffffff;"></i></a>
        <% } else { %>
            <a href="login.jsp"><i class="fa-solid fa-user-plus" style="color: #ffffff;"></i></a>
        <% } %>
    </div>
</nav>
</body>
</html>
