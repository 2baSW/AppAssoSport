<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.Adherent" %>
<html>
<head>
    <title>Formulaire Adhérent</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<h1>Formulaire Adhérent</h1>
<form action="adherents" method="post">
    <input type="hidden" name="action" value="save"/>
    <input type="hidden" name="id" value="<%= request.getAttribute("adherent") != null ? ((Adherent) request.getAttribute("adherent")).getId() : "" %>"/>
    <label>Nom :</label><input type="text" name="nom" value="<%= request.getAttribute("adherent") != null ? ((Adherent) request.getAttribute("adherent")).getNom() : "" %>" required/><br/>
    <label>Prénom :</label><input type="text" name="prenom" value="<%= request.getAttribute("adherent") != null ? ((Adherent) request.getAttribute("adherent")).getPrenom() : "" %>" required/><br/>
    <label>Email :</label><input type="email" name="email" value="<%= request.getAttribute("adherent") != null ? ((Adherent) request.getAttribute("adherent")).getEmail() : "" %>" required/><br/>
    <label>Téléphone :</label><input type="text" name="telephone" value="<%= request.getAttribute("adherent") != null ? ((Adherent) request.getAttribute("adherent")).getTelephone() : "" %>" required/><br/>
    <label>Adresse :</label><input type="text" name="adresse" value="<%= request.getAttribute("adherent") != null ? ((Adherent) request.getAttribute("adherent")).getAdresse() : "" %>" required/><br/>
    <label>Code Postal :</label><input type="text" name="codePostal" value="<%= request.getAttribute("adherent") != null ? ((Adherent) request.getAttribute("adherent")).getCodePostal() : "" %>" required/><br/>
    <label>Ville :</label><input type="text" name="ville" value="<%= request.getAttribute("adherent") != null ? ((Adherent) request.getAttribute("adherent")).getVille() : "" %>" required/><br/>
    <label>Date de Naissance :</label><input type="date" name="dateNaissance" value="<%= request.getAttribute("adherent") != null ? ((Adherent) request.getAttribute("adherent")).getDateNaissance() : "" %>" required/><br/>
    <label>Date Paiement Cotisation :</label><input type="date" name="datePaiementCotisation" value="<%= request.getAttribute("adherent") != null ? ((Adherent) request.getAttribute("adherent")).getDatePaiementCotisation() : "" %>" required/><br/>
    <button type="submit">Enregistrer</button>
</form>
<a href="adherents">Retour à la liste</a>
</body>
</html>
