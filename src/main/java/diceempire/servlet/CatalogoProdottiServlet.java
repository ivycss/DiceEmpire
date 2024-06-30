package diceempire.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import diceempire.model.Prodotto;
import diceempire.control.ProdottoModelMD;


public class CatalogoProdottiServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    static boolean isDataSource = true;
    static ProdottoModelMD model;

    static {
        if (isDataSource) {
            model = new ProdottoModelMD();
        } else {
            model = new ProdottoModelMD();
        }
    }

    public CatalogoProdottiServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String sort = request.getParameter("sort");
        String filter = request.getParameter("filter");

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
                    String nome = request.getParameter("nome");
                    String tipoProdotto = request.getParameter("tipoProdotto");
                    String tipoGioco = request.getParameter("tipoGioco");
                    String tipoCarte = request.getParameter("tipoCarte");
                    String produttore = request.getParameter("produttore");
                    String descLunga = request.getParameter("descrizioneLunga");
                    String descCorta = request.getParameter("descrizione");
                    String prezzoStr = request.getParameter("prezzo");
                    double prezzo;
                    if (!prezzoStr.isEmpty()) {
                        prezzo = Double.parseDouble(prezzoStr);
                    } else {
                        prezzo = 0.0;
                    }
                    int eta = Integer.parseInt(request.getParameter("eta"));
                    int edizione = Integer.parseInt(request.getParameter("edizione"));
                    String edizioneLimitata = request.getParameter("edizioneLimitata");
                    int quantita = Integer.parseInt(request.getParameter("quantita"));
                  //conversione immagine, legge tutto il flusso di byte che identificano l'immagine del prodotto
                    Part filePart = request.getPart("immagine");
                    byte[] immagine = null;
                    if (filePart != null && filePart.getSize() > 0) {
                        try (InputStream inputStream = filePart.getInputStream()) {
                            immagine = inputStream.readAllBytes();
                        } catch (IOException e) {
                            System.out.println("Errore nel leggere l'immagine: " + e.getMessage());
                        }
                    }
                    
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
                    prodotto.setImmagine(immagine);

                    model.doSave(prodotto);
                }
            }

            Collection<Prodotto> products;
            if (filter != null) {
                request.removeAttribute("products");
                request.setAttribute("products", model.doRetrieveByCategory(filter));
            } else {
                request.removeAttribute("products");
                request.setAttribute("products", model.doRetrieveAll(sort));//il metodo dp retrieve all può avere come attributo un order oppure niente
            }
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/catalogo.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}