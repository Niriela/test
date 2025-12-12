<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="test.entity.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Enregistrement Réussi</title>
</head>
<body>
    <h1>✓ Étudiant Enregistré avec Succès!</h1>
    
    <%
        Etudiant etudiant = (Etudiant) request.getAttribute("etudiant");
        if (etudiant != null) {
    %>
    
    <h2>Informations Personnelles</h2>
    <p><strong>ID:</strong> <%= etudiant.getId() %></p>
    <p><strong>Nom:</strong> <%= etudiant.getNom() %></p>
    <p><strong>Prénom:</strong> <%= etudiant.getPrenom() %></p>
    <p><strong>Email:</strong> <%= etudiant.getEmail() %></p>
    
    <% if (etudiant.getNotes() != null) { %>
    <hr>
    <h2>Informations sur les Notes</h2>
    <p><strong>Matière:</strong> <%= etudiant.getNotes().getMatiere() %></p>
    <p><strong>Note:</strong> <%= etudiant.getNotes().getNote() %> / 20</p>
    <p><strong>Moyenne:</strong> <%= etudiant.getNotes().getMoyenne() %></p>
    <% } %>
    
    <% } else { %>
        <p>Aucune donnée d'étudiant trouvée.</p>
    <% } %>
    
    <hr>
    <p><a href="${pageContext.request.contextPath}/formEtudiant">← Retour au formulaire</a></p>
</body>
</html>
