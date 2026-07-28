<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Panel</title>
</head>
<body>
    <h2>${title}</h2>
    <p>Si cette page s’affiche, le contrôle @Authorized/@Role fonctionne.</p>

    <a href="${pageContext.request.contextPath}/admin/login">Retour</a>
</body>
</html>