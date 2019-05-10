<%-- 
    Document   : footer
    Created on : 9 mai 2019, 15:45:40
    Author     : Marine Veyssiere
--%>
<c:choose>
<c:when test="${!empty sessionScope.sessionUtilisateur}"><p class="success">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.email}</p></c:when>
<c:otherwise><p>Vous n'êtes pas connecté(e) </p></c:otherwise>
</c:choose>

