package diceempire.servlet;

import diceempire.control.PagamentoModelMD;
import diceempire.model.Utente;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EliminaCartaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("auth");

        if (user != null) {
            PagamentoModelMD pagamentoModel = new PagamentoModelMD();
            try {
                boolean deleted = pagamentoModel.doDelete(user.getId());
                session.removeAttribute("carta");
                if (deleted) {
                    session.setAttribute("updateSuccess", "Carta eliminata correttamente.");
                } else {
                    session.setAttribute("updateError", "Eliminazione della carta fallita.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                session.setAttribute("updateError", "Errore durante l'eliminazione della carta.");
            }
        } else {
            session.setAttribute("updateError", "Nessun utente connesso.");
        }
        
        response.sendRedirect("usercarta.jsp");
    }
}
