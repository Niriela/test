<!-- filepath: d:\ITU_S5\Naina\FRAMEWORK\Sprint_framework\web\upload-form.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload Fichier - Sprint 9</title>
</head>
<body>
    <div class="container">
        <h2>📤 Upload de Fichiers - Sprint 9</h2>
        
        <div class="info">
            <strong>Info:</strong> Ce formulaire permet d'envoyer des données normales ET des fichiers.
        </div>

        <form action="${pageContext.request.contextPath}/user/upload" method="POST" enctype="multipart/form-data">
            
            <div class="form-group">
                <label for="firstName">Prénom:</label>
                <input type="text" id="firstName" name="user.firstName" placeholder="Entrez votre prénom" required>
            </div>

            <div class="form-group">
                <label for="lastName">Nom:</label>
                <input type="text" id="lastName" name="user.lastName" placeholder="Entrez votre nom" required>
            </div>

            <div class="form-group">
                <label for="photo">Photo de profil:</label>
                <input type="file" id="photo" name="photo" accept="image/*">
            </div>

            <div class="form-group">
                <label for="cv">CV (PDF):</label>
                <input type="file" id="cv" name="cv" accept=".pdf,.doc,.docx">
            </div>

            <div class="form-group">
                <label for="document">Autre document:</label>
                <input type="file" id="document" name="document">
            </div>

            <button type="submit">📤 Envoyer</button>
        </form>
    </div>
</body>
</html>