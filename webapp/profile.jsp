<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profil</title>
</head>
<body>
    <h2>Mon profil</h2>
    
    <% 
        String username = (String) request.getAttribute("username");
        Boolean isLoggedIn = (Boolean) request.getAttribute("isLoggedIn");
        
        if (isLoggedIn != null && isLoggedIn && username != null) {
    %>
        <p>Nom d'utilisateur: <strong><%= username %></strong></p>
        
        <ul>
            <li><a href="logout">Déconnexion</a></li>
        </ul>
    <% } else { %>
        <p>Vous devez être connecté pour voir cette page.</p>
        <a href="login">Se connecter</a>
    <% } %>
</body>
</html>
