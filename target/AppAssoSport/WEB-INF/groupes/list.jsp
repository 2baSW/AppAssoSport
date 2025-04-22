<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Groupe" %>
<html>
<head>
    <title>Liste des Groupes</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<h1>Liste des Groupes</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nom du Groupe</th>
        <th>Adhérents</th>
        <th>Actions</th>
    </tr>
    <%
    	@SuppressWarnings("unchecked")
        List<Groupe> groupes = (List<Groupe>) request.getAttribute("groupes");
        if (groupes != null && !groupes.isEmpty()) {
            for (Groupe groupe : groupes) {
    %>
        <tr>
            <td><%= groupe.getId() %></td>
            <td><%= groupe.getNom() %></td>
            <td>
                <ul>
                    <%
                        if (groupe.getAdherents() != null) {
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
            </td>
            <td>
                <a href="groupes?action=details&id=<%= groupe.getId() %>">Détails</a>
                <a href="groupes?action=edit&id=<%= groupe.getId() %>">Modifier</a>
                <a href="groupes?action=delete&id=<%= groupe.getId() %>" onclick="return confirm('Supprimer ce groupe ?');">Supprimer</a>
            </td>
        </tr>
    <%
            }
        } else {
    %>
        <tr>
            <td colspan="4">Aucun groupe trouvé</td>
        </tr>
    <%
        }
    %>
</table>
<a href="groupes?action=edit">Ajouter un Groupe</a>
</body>
</html>
