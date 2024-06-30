package diceempire.servlet;

import diceempire.control.ProdottoModelMD;
import diceempire.model.Cart;
import diceempire.model.Prodotto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

@WebServlet(name = "AddToCartServlet", urlPatterns = "/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ProdottoModelMD prodottoModelMD = new ProdottoModelMD();
            Prodotto prodotto = prodottoModelMD.doRetrieveByKey(id);

            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
            }

            cart.addProduct(prodotto);
            session.setAttribute("cart", cart);
            session.setAttribute("aggiuntoCarrello", "true");

            String pageVisited = request.getHeader("referer");//recupera l'header dell'utlima pagina visitata
            if (pageVisited != null && pageVisited.endsWith("dettagli.jsp")) {
            	pageVisited += "?id=" + id;
            }
            //l'aggiunta è stata fatta da una apgina dettagli. Si prende l'id del prodotto appena aggiunto e si torna alla sua pagina di dettagli
            response.sendRedirect(pageVisited);
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
