<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test JSP</title>
</head>
<body>
    <h1>Hello JSP 👋</h1>

    <%-- Petit scriptlet Java pour tester --%>
    <%
        String nom = "Nirii";
        out.println("<p>Bienvenue, " + nom + " !</p>");
    %>

</body>
</html>
