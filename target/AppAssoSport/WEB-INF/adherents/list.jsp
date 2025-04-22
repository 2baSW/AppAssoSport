<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Adherent" %>
<%@ page import="entity.Groupe" %>
<html>
<head>
    <title>Liste des Adhérents</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<h1>Liste des Adhérents</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Email</th>
        <th>Téléphone</th>
        <th>Adresse</th>
        <th>Code Postal</th>
        <th>Ville</th>
        <th>Date de Naissance</th>
        <th>Date Paiement Cotisation</th>
        <th>Groupe</th>
        <th>Actions</th>
    </tr>
    <%
    	@SuppressWarnings("unchecked")
        List<Adherent> adherents = (List<Adherent>) request.getAttribute("adherents");
    	@SuppressWarnings("unchecked")
        List<Groupe> groupes = (List<Groupe>) request.getAttribute("groupes");
        if (adherents != null && !adherents.isEmpty()) {
            for (Adherent adherent : adherents) {
    %>
        <tr>
            <td><%= adherent.getId() %></td>
            <td><%= adherent.getNom() %></td>
            <td><%= adherent.getPrenom() %></td>
            <td><%= adherent.getEmail() %></td>
            <td><%= adherent.getTelephone() %></td>
            <td><%= adherent.getAdresse() %></td>
            <td><%= adherent.getCodePostal() %></td>
            <td><%= adherent.getVille() %></td>
            <td><%= adherent.getDateNaissance() %></td>
            <td><%= adherent.getDatePaiementCotisation() %></td>
            <td>
                <%
                    if (adherent.getGroupe() != null) {
                        out.print(adherent.getGroupe().getNom());
                    } else {
                        out.print("Aucun groupe");
                    }
                %>
            </td>
            <td>
                <a href="adherents?action=details&id=<%= adherent.getId() %>">Détails</a>
                <a href="adherents?action=edit&id=<%= adherent.getId() %>">Modifier</a>
                <a href="adherents?action=delete&id=<%= adherent.getId() %>" 
                   onclick="return confirm('Voulez-vous vraiment supprimer cet adhérent ?');">Supprimer</a>
                <%
                    if (adherent.getGroupe() != null) {
                %>
                    <form action="adherents" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="removeGroup">
                        <input type="hidden" name="adherentId" value="<%= adherent.getId() %>">
                        <button type="submit">Retirer du Groupe</button>
                    </form>
                <%
                    } else {
                %>
                    <form action="adherents" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="assign">
                        <input type="hidden" name="adherentId" value="<%= adherent.getId() %>">
                        <select name="groupId">
                            <option value="" disabled selected>Choisir un groupe</option>
                            <%
                                for (Groupe groupe : groupes) {
                            %>
                                <option value="<%= groupe.getId() %>"><%= groupe.getNom() %></option>
                            <%
                                }
                            %>
                        </select>
                        <button type="submit">Assigner</button>
                    </form>
                <%
                    }
                %>
            </td>
        </tr>
    <%
            }
        } else {
    %>
        <tr>
            <td colspan="12">Aucun adhérent trouvé</td>
        </tr>
    <%
        }
    %>
</table>
<a href="adherents?action=edit">Ajouter un Adhérent</a>
</body>
</html>
