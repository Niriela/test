<html>
<body>
    <form action="${pageContext.request.contextPath}/form/save1" method="post">
        <label for="id">ID :</label>
        <input type="number" name="id" id="id" /><br>
        <label for="nom">Nom :</label>
        <input type="text" name="nom" id="nom" /><br>
        <button type="submit">Envoyer</button>
    </form>
</body>
</html>