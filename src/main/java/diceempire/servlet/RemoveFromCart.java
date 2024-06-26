package diceempire.servlet;


import diceempire.model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




@WebServlet(name = "RemoveFromCart", urlPatterns = "/remove-from-cart")
public class RemoveFromCart extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Ottieni l'ID del prodotto da rimuovere dal parametro della richiesta
        int productId = Integer.parseInt(request.getParameter("id"));

        // Recupera il carrello dalla sessione dell'utente
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        // Rimuovi il prodotto dal carrello
        if (cart != null) {
            cart.removeProdottiCarrello(productId);
        }

        // Aggiorna il carrello nella sessione
        session.setAttribute("cart", cart);

        // Redirect alla pagina del carrello o ad un'altra pagina
        response.sendRedirect("carrello.jsp");
    }
        
    }
    
