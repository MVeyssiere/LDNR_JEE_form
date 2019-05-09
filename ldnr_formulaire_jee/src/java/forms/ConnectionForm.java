package forms;

import java.util.HashMap;
import java.util.Map;

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


}
