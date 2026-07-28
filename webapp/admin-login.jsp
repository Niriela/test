<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Login</title>
</head>
<body>
    <h2>Connexion admin</h2>

    <p>${message}</p>

    <form action="${pageContext.request.contextPath}/admin/login" method="post">
        <div>
            <label>Nom d'utilisateur</label>
            <input type="text" name="username" />
        </div>

        <div>
            <label>Rôle</label>
            <input type="text" name="role" value="chef" />
        </div>

        <button type="submit">Créer la session</button>
    </form>

    <hr>

    <a href="${pageContext.request.contextPath}/admin/panel">Accéder au panneau protégé</a>
</body>
</html>