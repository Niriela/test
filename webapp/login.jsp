<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Connexion</h2>
    
    <% 
        // Vérifier si l'utilisateur est connecté via la session HTTP
        String userFromSession = (String) session.getAttribute("user");
        if (userFromSession != null) {
    %>
        <p><strong>Vous êtes déjà connecté en tant que: <%= userFromSession %></strong></p>
        <p>Session ID: <%= session.getId() %></p>
        <p>User ID: <%= session.getAttribute("userId") %></p>
        <p>Is Authenticated: <%= session.getAttribute("isAuthenticated") %></p>
        <br>
        <a href="profile">Voir mon profil</a> | <a href="logout">Se déconnecter</a>
        <hr>
    <% } %>
    
    <% if (request.getAttribute("error") != null) { %>
        <p><%= request.getAttribute("error") %></p>
    <% } %>
    
    <form action="login" method="post">
        <div>
            <label for="username">Nom d'utilisateur:</label>
            <input type="text" id="username" name="username" required>
        </div>
        
        <div>
            <label for="password">Mot de passe:</label>
            <input type="password" id="password" name="password" required>
        </div>
        
        <button type="submit">Se connecter</button>
    </form>
</body>
</html>
