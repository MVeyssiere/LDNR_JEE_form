package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Herbert Caffarel <herbert.caffarel@cicef.pro>
 */
public class Inscription extends HttpServlet {

    /* Des constantes */
    public static final String VIEW = "/WEB-INF/inscription.jsp";
    private static final String EMAIL = "email";
    private static final String PWD = "password";
    private static final String CONFIRM = "confirm";
    private static final String USERNAME = "username";

    private static final String ERRORS = "errors";
    private static final String RESULT = "result";


    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* Affichage de la page d'inscription */
        this.getServletContext()
                .getRequestDispatcher(VIEW)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String result;
        Map<String, String> errors = new HashMap<>();

        /*  récupération des paramètres */
        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PWD);
        String confirm = req.getParameter(CONFIRM);
        String userName = req.getParameter(USERNAME);

        /* gestion des cas d'erreur pour chaque méthode de vérification*/
        try {
            validateEmail(email);
        } catch (Exception ex) {
//                Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
            errors.put(EMAIL, ex.getMessage());
        }
        try {
            validatePassword(password, confirm);
        } catch (Exception ex) {
            errors.put(PWD, ex.getMessage());
        }
        try {
            validateName(userName);
        } catch (Exception ex) {
            errors.put(USERNAME, ex.getMessage());
        }

        /* gestion affichage du message d'erreur*/
        if (errors.isEmpty()) {
            result = "Succès de l'inscription";
        } else {
            result = "Echec de l'inscription";
        }

        /* stockage erreurs et results dans l'objet request*/
        req.setAttribute(ERRORS, errors);
        req.setAttribute(RESULT, result);

        /* Transmission des objets request/response au JSP*/
        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    // champ email valide
    private void validateEmail(String email) throws Exception {
        if (!email.isEmpty()) {  //si la case mail est remplie
            //si mail non valide
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new Exception("Le format de l'email n'est pas valide.");
            }
        }
        else {
            throw new Exception("Renseignez votre adresse mail");
        }

    }

    // password et confirm ont 3 lettres au moins et sont identiques
    private void validatePassword(String password, String confirm) throws Exception {
        if (!password.isEmpty() && !confirm.isEmpty()) {
            if (!password.equals(confirm)) {
                throw new Exception("Vos mots de passe doivent être identiques.");
            } else {
                throw new Exception("Votre mot de passe doit contenir au moins 3 lettres.");
            }
        } else {
            if (password.isEmpty()) {
                throw new Exception("Veuillez entrer votre mot de passe.");
            }
            if (confirm.isEmpty()) {
                throw new Exception("Veuillez confirmer votre mot de passe.");
            }
        }
    }

    // champ facultatif name >2 caractères
    private void validateName(String name) throws Exception {
        if (name.isEmpty()) {
            throw new Exception("Le nom d'utilisateur doit contenir au moins 2 caractères.");
        }
    }

}
