package forms;

import DAO.DAOUser;
import beans.User;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Marine V
 */
public class ConnectionForm {

    private static final String EMAIL = "email";
    private static final String PASS = "password";

    private String result;
    private final Map<String, String> errors = new HashMap<>();

    public String getResult() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public User connectUser(HttpServletRequest request) {

        String mail = getParamValue(request, EMAIL);
        String pwd = getParamValue(request, PASS);

        User user = new User();

        try {
            validateEmail(mail);
        } catch (Exception e) {
            setErrors(EMAIL, e.getMessage());
        }

        DAOUser daouser = new DAOUser();
        if (daouser.verifyEmail(mail)) {
            user.setEmail(mail);
        } else {
            setErrors(EMAIL, "Vous n'êtes pas inscrit. Veuillez vous rendre sur la page d'inscription.");
        }

        //System.out.println(daouser.verifyEmail(mail));

        try {
            validatePassword(pwd);
        } catch (Exception e) {
            setErrors(PASS, e.getMessage());
        }
        System.out.println("password: " + pwd);
        System.out.println("password encrypté: " + encryptThisString(pwd));
        System.out.println("password from db: " + daouser.findFromEmail(mail).getPassword());
        if (encryptThisString(pwd).matches(daouser.findFromEmail(mail).getPassword())) {
            user.setPassword(pwd);
        } else {
            setErrors(PASS, "Mot de passe incorrect. Veuillez le ressaisir.");
        }

        /* Initialisation du résultat global de la validation. */
        if (errors.isEmpty()) {
            result = "Succès de la connexion.";
        } else {
            result = "Échec de la connexion.";
        }
        return user;
    }

    private static String encryptThisString(String input) {
        try {
            // getInstance() method is called with algorithm SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            // return the HashText
            return hashtext;
        } // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
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
    private void validatePassword(String password) throws Exception {
        if (!password.isEmpty()) {
            if (!password.matches("^.{3,}$")) {
                throw new Exception("Votre mot de passe doit contenir au moins 3 lettres.");
            }
        } else {
            throw new Exception("Merci de saisir un mot de passe");
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

}
