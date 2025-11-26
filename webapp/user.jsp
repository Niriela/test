<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test JSP</title>
</head>
<body>
  <form method="POST" action="/user/save1">
    <input type="text" name="firstName" required>
    <input type="text" name="lastName" required>
    <input type="email" name="email" required>
    <button type="submit">S'enregistrer</button>
</form>

</body>
</html>
