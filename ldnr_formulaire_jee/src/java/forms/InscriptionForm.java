package forms;

import beans.User;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Marine V
 */
public final class InscriptionForm {

    private static final String EMAIL = "email";
    private static final String PWD = "password";
    private static final String CONFIRM = "confirm";
    private static final String USERNAME = "username";

    String result;
    Map<String, String> errors = new HashMap<>();

    public String getResult() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    // retourne null si le champ est vide ou null, ou sa valeur « trimée » sinon, ce qui permet de simplifier les tests.
    private static String getParamValue(HttpServletRequest request, String paramKey) {
        String value = request.getParameter(paramKey);
        if (value == null || value.trim().length() == 0) {
            return null;
        } else {
            return value.trim();
        }
    }

    // champ email valide
    private void validateEmail(String email) throws Exception {
        if (!email.isEmpty()) {  //si la case mail est remplie
            //si mail non valide
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new Exception("Le format de l'email n'est pas valide.");
            }
        } else {
            throw new Exception("Merci de saisir une adresse mail.");
        }
    }

    // password et confirm ont 3 lettres au moins et sont identiques
    private void validatePassword(String password, String confirm) throws Exception {
        if (!password.isEmpty() && !confirm.isEmpty()) {
            if (!password.equals(confirm)) {
                throw new Exception("Les mots de passe entrés sont différents. Merci de les saisir à nouveau.");
            } else {
                if (!password.matches("^.{3,}$")) {
                    throw new Exception("Votre mot de passe doit contenir au moins 3 lettres.");
                }
            }
        } else {
            if (password.isEmpty()) {
                throw new Exception("Merci de saisir un mot de passe");
            }
            if (confirm.isEmpty()) {
                throw new Exception("Veuillez confirmer votre mot de passe.");
            }
        }
    }

    // champ facultatif name >2 caractères
    private void validateName(String name) throws Exception {
        if (!name.isEmpty() && !name.matches("^[a-zA-Z]{3,}")) {
            throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");
        }
        if (name.isEmpty()) {
            throw new Exception("Merci de saisir un nom d'utilisateur.");
        }
    }

    private void setErrors(String field, String message) {
        errors.put(field, message);
    }

    public User inscribeUser(HttpServletRequest request) {
        /* Récupération des champs du formulaire. */
        String mail = getParamValue(request, EMAIL);
        String pass = getParamValue(request, PWD);
        String confirm = getParamValue(request, CONFIRM);
        String name = getParamValue(request, USERNAME);

        User user = new User();
        // setting of values of the bean object user
        user.setEmail(EMAIL);
        user.setName(name);
        user.setPassword(pass);

        try {
            validateEmail(mail);
        } catch (Exception ex) {
//                Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
            setErrors(EMAIL, ex.getMessage());
        }
        try {
            validatePassword(pass, confirm);
        } catch (Exception ex) {
            setErrors(PWD, ex.getMessage());
        }
        try {
            validateName(name);
        } catch (Exception ex) {
            setErrors(USERNAME, ex.getMessage());
        }

        /* gestion affichage du message d'erreur*/
        if (errors.isEmpty()) {
            result = "Succès de l'inscription";
        } else {
            result = "Echec de l'inscription";
        }

        return user;

    }
}
