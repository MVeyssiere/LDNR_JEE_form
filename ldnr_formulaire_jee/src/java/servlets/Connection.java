package servlets;

import beans.User;
import forms.ConnectionForm;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Marine V
 */
public class Connection extends HttpServlet {

    private static final String ATT_USER = "utilisateur";
    private static final String ATT_FORM = "form";
    private static final String ATT_SESSION_USER = "sessionUtilisateur";
    private static final String VUE = "/WEB-INF/connexion.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        ConnectionForm form = new ConnectionForm();
        /* Traitement de la requête et récupération du bean en résultant */
        User user = form.connectUser(req);
        /* Récupération de la session depuis la requête */
        HttpSession session = req.getSession();
        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean user
         * à la session, sinon suppression du bean de la session.
         */
        if (form.getErrors().isEmpty()) {
            session.setAttribute(ATT_SESSION_USER, user);
        } else {
            session.setAttribute(ATT_SESSION_USER, null);
        }

        /* Stockage du formulaire et du bean dans l'objet request */
        req.setAttribute(ATT_FORM, form);
        req.setAttribute(ATT_USER, user);
        this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
    }

}
