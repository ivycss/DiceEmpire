package diceempire.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.io.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.servlet.*;

import diceempire.model.*;
import diceempire.control.*;

@WebServlet("/AdminCatalogServlet")
//per gestione e limite dei dati caricati, per gestire l'aggiunta delle immagini
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class AdminCatalogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static boolean isDataSource = true;
    static ProdottoModel model;

    static {
        if (isDataSource) {
            model = new ProdottoModelMD();
        } else {
            model = new ProdottoModelMD();
        }
    }

    public AdminCatalogServlet() {
        super();
        System.out.println("Costruttore AdminCatalogServlet chiamato.");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if (action != null) {
                if (action.equalsIgnoreCase("read")) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.removeAttribute("product");
                    request.setAttribute("product", model.doRetrieveByKey(id));
                } else if (action.equalsIgnoreCase("delete")) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    model.doDelete(id);//dal model di prodotto
                } else if (action.equalsIgnoreCase("insert")) {
                    // nullas da fare per l'azione "insert" nel doGet
                }
            }
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }

        String sort = request.getParameter("sort");

        try {
            request.removeAttribute("products");
            request.setAttribute("products", model.doRetrieveAll(sort));
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/catalogoadmin.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	System.out.println("almeno ci ha provato 1");
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(request.getParameter("nome"));
        prodotto.setTipoProdotto(request.getParameter("tipoProdotto"));
        prodotto.setTipoGioco(request.getParameter("tipoGioco"));
        prodotto.setTipoCarte(request.getParameter("tipoCarte"));
        prodotto.setProduttore(request.getParameter("produttore"));
        prodotto.setDescLunga(request.getParameter("descrizioneLunga"));
        prodotto.setDescCorta(request.getParameter("descrizione"));
        prodotto.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
        prodotto.setIVA(Double.parseDouble(request.getParameter("iva")));
        prodotto.setEta(Integer.parseInt(request.getParameter("eta")));
        prodotto.setEdizione(Integer.parseInt(request.getParameter("edizione")));
        prodotto.setEdizioneLimitata(request.getParameter("edizioneLimitata"));
        prodotto.setQuantita(Integer.parseInt(request.getParameter("quantita")));

        Part filePart = request.getPart("immagine");
        if (filePart != null && filePart.getSize() > 0) {
        	//prende solo una parte della request, la foto, e dice quanti byte dovrà leggere
            try (InputStream inputStream = filePart.getInputStream()) {
                byte[] imageBytes = new byte[(int) filePart.getSize()];
                inputStream.read(imageBytes);
                prodotto.setImmagine(imageBytes);
            }
        }

        try {
            String action = request.getParameter("action");
            if (action.equalsIgnoreCase("insert")) {
            	 // azione di default per l'inserimento di un nuovo prodotto
                model.doSave(prodotto);
                System.out.println("almeno ci ha provato 2");
            } else if (action.equalsIgnoreCase("update")) {
            	 // azione di aggiornamento di un prodotto esistente
            	Integer id = Integer.parseInt(request.getParameter("id"));
                System.out.println("ID del prodotto: " + id);
                if (id != null) {
                	System.out.println("almeno ci ha provato 3");
                	System.out.println("ID del prodotto2: " + id);
                    model.doUpdate(prodotto,id);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
            response.sendRedirect("error.jsp");  
            return;
        }

        response.sendRedirect(request.getContextPath() + "/catalogoadmin.jsp");
    }

}
