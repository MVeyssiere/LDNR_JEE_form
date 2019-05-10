package servlets;

import DAO.DAOUser;
import beans.User;
import forms.InscriptionForm;
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
    private static final String ATT_FORM = "form";
    private static final String ATT_USER = "user";

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
        DAOUser daouser = new DAOUser();

        /* Affichage de la page d'inscription */
        this.getServletContext()
                .getRequestDispatcher(VIEW)
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // création de l'objet form
        InscriptionForm form = new InscriptionForm();
        // Création de l'objet user
        User user = form.inscribeUser(req);

        /* stockage formulaire et bean dans l'objet request*/
        req.setAttribute(ATT_FORM, form);
        req.setAttribute(ATT_USER, user);

        /* Transmission des objets request/response au JSP*/
        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }
}
