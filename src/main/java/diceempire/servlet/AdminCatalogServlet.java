package diceempire.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.io.*;
import java.nio.file.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import diceempire.model.*;
import diceempire.control.*;

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
                    model.doDelete(id);
                } else if (action.equalsIgnoreCase("insert")) {
                    
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insert".equalsIgnoreCase(action)) {
            
            String uploadPath = getServletContext().getRealPath("") + File.separator + "images";

            
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            Part filePart = request.getPart("immagine");
            String fileName = getFileName(filePart);

            if (fileName != null && !fileName.isEmpty()) {
                
                String filePath = uploadPath + File.separator + fileName;
                filePart.write(filePath);

                
                String nome = request.getParameter("nome");
                String tipoProdotto = request.getParameter("tipoProdotto");
                String tipoGioco = request.getParameter("tipoGioco");
                String tipoCarte = request.getParameter("tipoCarte");
                String produttore = request.getParameter("produttore");
                String descLunga = request.getParameter("descrizioneLunga");
                String descCorta = request.getParameter("descrizione");
                String prezzoStr = request.getParameter("prezzo");
                double prezzo = (prezzoStr != null && !prezzoStr.isEmpty()) ? Double.parseDouble(prezzoStr) : 0.0;
                int eta = Integer.parseInt(request.getParameter("eta"));
                int edizione = Integer.parseInt(request.getParameter("edizione"));
                String edizioneLimitata = request.getParameter("edizioneLimitata");
                int quantita = Integer.parseInt(request.getParameter("quantita"));

                Prodotto prodotto = new Prodotto();
                prodotto.setNome(nome);
                prodotto.setTipoProdotto(tipoProdotto);
                prodotto.setTipoGioco(tipoGioco);
                prodotto.setTipoCarte(tipoCarte);
                prodotto.setProduttore(produttore);
                prodotto.setDescLunga(descLunga);
                prodotto.setDescCorta(descCorta);
                prodotto.setPrezzo(prezzo);
                prodotto.setEta(eta);
                prodotto.setEdizione(edizione);
                prodotto.setEdizioneLimitata(edizioneLimitata);
                prodotto.setQuantita(quantita);
                prodotto.setNomeImmagine(fileName);

                try {
                    model.doSave(prodotto);
                } catch (SQLException e) {
                    System.out.println("Error:" + e.getMessage());
                }
            }

            
            response.sendRedirect(request.getContextPath() + "/catalogoadmin.jsp");
        } else {
            
            doGet(request, response);
        }
    }

    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
