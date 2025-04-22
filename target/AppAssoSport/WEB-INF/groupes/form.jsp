<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.Groupe" %>
<html>
<head>
    <title>Formulaire Groupe</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<h1><%= request.getAttribute("groupe") == null ? "Ajouter un Groupe" : "Modifier le Groupe" %></h1>
<form action="groupes" method="post">
    <input type="hidden" name="action" value="save">
    <input type="hidden" name="id" value="<%= request.getAttribute("groupe") != null ? ((Groupe) request.getAttribute("groupe")).getId() : "" %>">
    <label for="nom">Nom du Groupe:</label>
    <input type="text" name="nom" id="nom" value="<%= request.getAttribute("groupe") != null ? ((Groupe) request.getAttribute("groupe")).getNom() : "" %>">
    <br>
    <input type="submit" value="Enregistrer">
    <a href="groupes">Annuler</a>
</form>
</body>
</html>
