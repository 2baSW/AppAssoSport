<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.Adherent" %>
<html>
<head>
    <title>Détails de l'Adhérent</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<h1>Détails de l'Adhérent</h1>
<%
    Adherent adherent = (Adherent) request.getAttribute("adherent");
    if (adherent != null) {
%>
    <p><strong>Nom :</strong> <%= adherent.getNom() %></p>
    <p><strong>Prénom :</strong> <%= adherent.getPrenom() %></p>
    <p><strong>Email :</strong> <%= adherent.getEmail() %></p>
    <p><strong>Téléphone :</strong> <%= adherent.getTelephone() %></p>
    <p><strong>Adresse :</strong> <%= adherent.getAdresse() %></p>
    <p><strong>Code Postal :</strong> <%= adherent.getCodePostal() %></p>
    <p><strong>Ville :</strong> <%= adherent.getVille() %></p>
    <p><strong>Date de Naissance :</strong> <%= adherent.getDateNaissance() %></p>
    <p><strong>Date Paiement Cotisation :</strong> <%= adherent.getDatePaiementCotisation() %></p>
<%
    } else {
%>
    <p>Aucun adhérent trouvé.</p>
<%
    }
%>
<a href="adherents">Retour à la liste</a>
</body>
</html>
