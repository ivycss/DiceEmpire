package diceempire.servlet;


import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import diceempire.model.*;
import diceempire.control.*;

public class CatalogServlet extends HttpServlet{


		private static final long serialVersionUID = 1L;

		// ProductModelDS usa il DataSource
		// ProductModelDM usa il DriverManager
		
		static boolean isDataSource = true;
		
		static ProdottoModelMD model;
		
		static {
			if (isDataSource) {
				model = new ProdottoModelMD();
			} else {
				model = new ProdottoModelMD();
			}
		}
		
		public CatalogServlet() {
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
						//prende parte del file che è l'immagine e ne elabora il flusso di byte
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

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doGet(request, response);
		}

	


}

