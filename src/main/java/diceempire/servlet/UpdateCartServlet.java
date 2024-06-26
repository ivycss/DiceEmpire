package diceempire.servlet;

import diceempire.model.Cart;
import diceempire.model.ProdottoInCarrello;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateCartServlet", urlPatterns = "/update-cart")
public class UpdateCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart != null) {
            List<ProdottoInCarrello> cartList = cart.getProdottiCarrello();
            for (ProdottoInCarrello item : cartList) {
                if (item.getItem().getId() == id) {
                    if (action.equals("increment")) {
                        item.incrementaNumero();
                    } else if (action.equals("decrement")) {
                        if (item.getNumItems() > 1) {
                            item.decrementaNumero();
                        } else {
                            cartList.remove(item);
                        }
                    }
                    break;
                }
            }
            cart.setProdottiCarrello(cartList);
            session.setAttribute("cart", cart);
        }

        response.sendRedirect("carrello.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
