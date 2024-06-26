package diceempire.servlet;

import diceempire.model.*;
import diceempire.control.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart carrello = (Cart) session.getAttribute("cart");
        Utente user = (Utente) session.getAttribute("auth");
        Integer idUtente = user.getId();

        PagamentoModelMD pagamentoModel = new PagamentoModelMD();
        try {
            Carta carta = pagamentoModel.doRetrieveByUser(idUtente);
            if (carta==null) {

                String numeroCartaStr = request.getParameter("numeroCarta");
                Long numeroCarta = Long.parseLong(numeroCartaStr);
                String nomeInt = request.getParameter("nomeInt");
                String cognomeInt = request.getParameter("cognomeInt");
                String cvcStr = request.getParameter("cvc");
                int cvc = Integer.parseInt(cvcStr);
                String dataScadenzaStr = request.getParameter("dataScadenza");
                int dataScadenza = Integer.parseInt(dataScadenzaStr);

                carta = new Carta();
                carta.setNumeroCarta(numeroCarta);
                carta.setNomeInt(nomeInt);
                carta.setCognomeInt(cognomeInt);
                carta.setCvc(cvc);
                carta.setDataScadenza(dataScadenza);
                carta.setIdUtente(idUtente);

                boolean success = pagamentoModel.doSave(carta);
                if (success) {
                    session.setAttribute("pagamentoSuccesso", true);
                } else {
                    throw new ServletException("Errore durante il salvataggio dei dati di pagamento");
                }
            }


            if (carrello == null || carrello.getProdottiCarrello().isEmpty()) {
                System.out.println("Il carrello è stato ricevuto vuoto");
                response.sendRedirect("cart.jsp");
                return;
            }
            System.out.println("Il carrello è stato ricevuto");

            Ordine ordine = new Ordine(null);
            List<ProdottoInCarrello> prodottiClonati = new ArrayList<>();
            for (ProdottoInCarrello prodotto : carrello.getProdottiCarrello()) {
                Prodotto clonedProdotto = new Prodotto();
                clonedProdotto.setId(prodotto.getItem().getId());
                clonedProdotto.setPrezzo(prodotto.getItem().getPrezzo());
                clonedProdotto.setDescCorta(prodotto.getItem().getDescCorta());
                clonedProdotto.setIVA(prodotto.getItem().getIVA());
                clonedProdotto.setNomeImmagine(prodotto.getNomeImmagine());
                clonedProdotto.setNome(prodotto.getItem().getNome());
                System.out.println("Id clone di prodotto " + clonedProdotto.getNome());
                ProdottoInCarrello prodOrd = new ProdottoInCarrello();
                prodOrd.setItem(clonedProdotto);
                prodOrd.setNumItems(prodotto.getNumItems());
                prodottiClonati.add(prodOrd);
            }
            ordine.setProdottiOrdine(prodottiClonati);
            for(ProdottoInCarrello prodotto : ordine.getProdottiOrdine()) {
                System.out.println("Id prodotto: " + prodotto.getItem().getId());
            }

            session.setAttribute("ordine", ordine);
            session.setAttribute("aggiuntoOrdine", "true");

            if(ordine.getProdottiOrdine().size() <= 0) {
                System.out.println("L'ordine è vuoto");
            }

            OrdineModelMD ordineModel = new OrdineModelMD();
            boolean ordineSalvato = ordineModel.doSave(idUtente);

            if (ordineSalvato) {
                Ordine ordineSalvatoDettagli = ordineModel.doRetrieveByUser(idUtente);
                ordine.setIdOrdine(ordineSalvatoDettagli.getIdOrdine());

                OrdineDettagliModelMD ordineDettagliModel = new OrdineDettagliModelMD();
                boolean dettagliSalvati = ordineDettagliModel.doSave(ordine);

                if (dettagliSalvati) {
                    session.removeAttribute("cart");
                    response.sendRedirect("login-signup/ordineok.jsp");
                } else {
                    throw new ServletException("Errore nel salvataggio dei dettagli dell'ordine");
                }
            } else {
                throw new ServletException("Errore nel salvataggio dell'ordine");
            }
        } catch (SQLException e) {
            throw new ServletException("Errore nel salvataggio dell'ordine", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart carrello = (Cart) session.getAttribute("cart");
        Utente user = (Utente) session.getAttribute("auth");

        if (carrello == null || carrello.getProdottiCarrello().isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }
        
        if (user == null) {
            response.sendRedirect("login-signup/needlogin.jsp");
            return;
        }

        session.setAttribute("prodottiCheckout", carrello.getProdottiCarrello());
        response.sendRedirect("checkout.jsp");
    }
}
