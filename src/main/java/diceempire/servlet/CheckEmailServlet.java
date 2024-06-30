package diceempire.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import diceempire.model.*;
import diceempire.control.*;


public class CheckEmailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static UtenteModelMD model = new UtenteModelMD();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        JsonObject jsonResponse = new JsonObject();

        try {
            Utente existingUser = model.doRetrieveByKey(email);
            if (existingUser != null) {
                jsonResponse.addProperty("exists", true);
            } else {
                jsonResponse.addProperty("exists", false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            jsonResponse.addProperty("exists", false);
        }

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
    }
}
