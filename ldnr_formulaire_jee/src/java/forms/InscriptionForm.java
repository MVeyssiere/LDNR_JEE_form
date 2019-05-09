package forms;

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

    public void inscribeUser(HttpServletRequest request) {
        /* Récupération des champs du formulaire. */
        String mail = getParamValue(request, EMAIL);
        String pass = getParamValue(request, PWD);
        String confirm = getParamValue(request, CONFIRM);
        String name = getParamValue(request, USERNAME);

    }

}
