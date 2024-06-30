package diceempire.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import diceempire.model.*;
import diceempire.control.*;

public class UpdateCardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("auth");
        PagamentoModelMD pagamentoModel = new PagamentoModelMD();
        
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            long numeroCarta = Long.parseLong(request.getParameter("numeroCarta"));
            int dataScadenza = Integer.parseInt(request.getParameter("dataScadenza"));
            int cvc = Integer.parseInt(request.getParameter("cvc"));
            String nomeInt = request.getParameter("nomeInt");
            String cognomeInt = request.getParameter("cognomeInt");

            Carta carta = new Carta();
            carta.setNumeroCarta(numeroCarta);
            carta.setDataScadenza(dataScadenza);
            carta.setCvc(cvc);
            carta.setNomeInt(nomeInt);
            carta.setCognomeInt(cognomeInt);
            carta.setIdUtente(user.getId());

            Carta cartaPresente = pagamentoModel.doRetrieveByUser(user.getId());
            if (cartaPresente == null) {
                // La carta non esiste, quindi salva una nuova carta
                boolean saveSuccess = pagamentoModel.doSave(carta);
                if (saveSuccess) {
                    session.setAttribute("updateSuccess", "Carta aggiunta con successo.");
                    session.setAttribute("carta", carta);
                } else {
                    session.setAttribute("updateError", "Errore durante l'aggiunta della carta.");
                }
            } else {
                // La carta esiste, quindi aggiornala
                Carta updatedCarta = pagamentoModel.doUpdate(carta);
                if (updatedCarta != null) {
                    session.setAttribute("updateSuccess", "Carta aggiornata con successo.");
                    session.setAttribute("carta", updatedCarta);
                } else {
                    session.setAttribute("updateError", "Errore durante l'aggiornamento della carta.");
                }
            }

            response.sendRedirect("usercarta.jsp");
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            session.setAttribute("updateError", "Errore durante l'aggiornamento della carta.");
            response.sendRedirect("errorcarta.jsp");
        }
    }
}