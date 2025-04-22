<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.Groupe" %>
<html>
<head>
    <title>Détails du Groupe</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<%
    Groupe groupe = (Groupe) request.getAttribute("groupe");
%>
<h1>Détails du Groupe</h1>
<p><strong>ID:</strong> <%= groupe.getId() %></p>
<p><strong>Nom:</strong> <%= groupe.getNom() %></p>
<h2>Adhérents</h2>
<ul>
    <%
        if (groupe.getAdherents() != null && !groupe.getAdherents().isEmpty()) {
            for (var adherent : groupe.getAdherents()) {
    %>
        <li><%= adherent.getNom() %> <%= adherent.getPrenom() %></li>
    <%
            }
        } else {
    %>
        <li>Aucun adhérent</li>
    <%
        }
    %>
</ul>
<a href="groupes">Retour à la liste</a>
</body>
</html>
