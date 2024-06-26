package diceempire.servlet;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diceempire.control.*;
import diceempire.model.*;
import diceempire.connection.*;

public class CartServlet extends HttpServlet{


		private static final long serialVersionUID = 1L;

		// ProductModelDS usa il DataSource
		// ProductModelDM usa il DriverManager
		
		static boolean isDataSource = true;
		
		static CartModelMD model;
		
		static {
			if (isDataSource) {
				model = new CartModelMD();
			} else {
				model = new CartModelMD();
			}
		}
		
		public CartServlet() {
			super();
		}

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			String action = request.getParameter("action");

			try {
				if (action != null) {
					if (action.equalsIgnoreCase("read")) {
						int id = Integer.parseInt(request.getParameter("id"));
						request.removeAttribute("prodotto");
						request.setAttribute("prodotto", model.doRetrieveByKey(id));
					} 
				}
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/carrello.jsp");
			dispatcher.forward(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doGet(request, response);
		}

	


}

