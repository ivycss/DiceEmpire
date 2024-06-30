package diceempire.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diceempire.control.ProdottoModelMD;
import diceempire.model.Prodotto;


public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        ProdottoModelMD prodottoModel = new ProdottoModelMD();
        Collection<Prodotto> prodotti = new LinkedList<Prodotto>();

        try {
            prodotti = prodottoModel.doRetrieveByQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        for (Prodotto prodotto : prodotti) {
            out.println("<div class='search-result' data-id='" + prodotto.getId() + "'>" + prodotto.getNome() + "</div>");
        }
    }
}
