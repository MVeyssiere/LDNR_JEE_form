package servlets;

import java.io.IOException;
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

        String email = req.getParameter("email");


    }

    // champ email valide
    private void validateEmail() {

    }

    // password et confirm ont 3 lettres au moins et sont identiques
    private void validatePassword() {

    }

    // champ facultatif name >2 caractères
    private void validateName() {

    }

}
