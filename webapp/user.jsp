<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Test JSP</title></head>
<body>
        <h1> ETU003348 </h1>

  <form method="POST" action="${pageContext.request.contextPath}/save">
    <p> Nom <input type="text" name="firstName" required> </p>
    <p> Prénom <input type="text" name="lastName" required> </p>
    <p> Email <input type="email" name="email" required> </p>
    <button type="submit">S'enregistrer</button>
  </form>
</body>
</html>
