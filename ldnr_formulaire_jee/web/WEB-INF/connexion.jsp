<%-- 
    Document   : connexion
    Created on : 9 mai 2019, 15:13:34
    Author     : Marine Veyssiere
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Connexion</title>
        <link type="text/css" rel="stylesheet" href="inc/form.css" />
    </head>
    <body>
        <form method="post" action="connection">
        <fieldset>
        <legend>Connexion</legend>
        <p>Vous pouvez vous connecter via ce formulaire.</p>
        <label for="email">Adresse email <span class="requis">*</span></label>
        <input type="email" id="email" name="email" value="<c:out value="${user.email}"/>" size="20" maxlength="60" />
        <span class="error">${form.errors['email']}</span>
        <br /><label for="password">Mot de passe <span class="requis">*</span></label>
        <input type="password" id="password" name="password" value="" size="20" maxlength="20" />
        <span class="error">${form.errors['password']}</span>
        <br /><input type="submit" value="Connexion" class="noLabel" />
        <p class="${empty form.errors ? 'success' : 'error'}">${form.result}</p>
        </fieldset>
        </form>
    </body>
</html>