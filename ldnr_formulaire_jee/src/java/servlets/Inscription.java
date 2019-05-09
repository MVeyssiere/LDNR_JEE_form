package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        // champs obligatoires non vides

        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PWD);
        String confirm = req.getParameter(CONFIRM);
        String userName = req.getParameter(USERNAME);

        try {
            validateEmail(email);
            validatePassword(password, confirm);
            validateName(userName);
        } catch (Exception ex) {
            Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
        }

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
                throw new Exception("Vos mots de passe ne sont pas identiques.");
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
    private void validateName(String name) {

    }

}
