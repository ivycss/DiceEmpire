package diceempire.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diceempire.model.*;
import diceempire.control.*;

@WebServlet ("/user-signup")
public class RegistrazioneServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static boolean isDataSource = true;
    static UtenteModelMD model;

    static {
        if (isDataSource) {
            model = new UtenteModelMD();
        } else {
            model = new UtenteModelMD();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pw = request.getParameter("password");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String cf = request.getParameter("cf");
        String telefonoStr = request.getParameter("numeroTelefono");
        String ageStr = request.getParameter("eta");
        String mail = request.getParameter("mail");
        String citta = request.getParameter("citta");
        String capStr = request.getParameter("cap");
        String via = request.getParameter("via");

        System.out.println("Parametri ricevuti:");
        System.out.println("password: " + pw);
        System.out.println("nome: " + nome);
        System.out.println("cognome: " + cognome);
        System.out.println("cf: " + cf);
        System.out.println("numeroTelefono: " + telefonoStr);
        System.out.println("età : " + ageStr);
        System.out.println("mail: " + mail);
        System.out.println("citta: " + citta);
        System.out.println("cap: " + capStr);
        System.out.println("via: " + via);

        if (telefonoStr == null || telefonoStr.isEmpty() || ageStr == null || ageStr.isEmpty() || capStr == null || capStr.isEmpty()) {
            request.setAttribute("error", "Tutti i campi sono obbligatori.");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/signup.jsp");
            dispatcher.forward(request, response);
            return;
        }

        int telefono = 0;
        int age = 0;
        int cap = 0;

        try {
            telefono = Integer.parseInt(telefonoStr);
            age = Integer.parseInt(ageStr);
            cap = Integer.parseInt(capStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "I campi Numero di Telefono, Età  e CAP devono essere numerici.");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login-signup/signuperror.jsp");
            dispatcher.forward(request, response);
            return;
        }

        UtenteModelMD utenteModel = new UtenteModelMD(); 
        Utente newUser = null;
        try {
            newUser = utenteModel.doRetrieveByKey(mail);
        } catch (SQLException e1) {
            e1.printStackTrace();
            request.setAttribute("error", "Errore durante la verifica dell'email. Riprova più tardi.");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login-signup/signuperror.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (newUser != null) {
            request.setAttribute("error", "Email già  registrata.");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login-signup/signupmail.jsp");
            dispatcher.forward(request, response);
            return;
        }
        String hashedPassword = hashPassword(pw);
        
        Utente user = new Utente();
        user.setCf(cf);
        user.setPassword(hashedPassword);
        user.setNome(nome);
        user.setCognome(cognome);
        user.setAge(age);
        user.setTelefono(telefono);
        user.setMail(mail);
        user.setCitta(citta);
        user.setCap(cap);
        user.setVia(via);

        try {
            utenteModel.doSave(user);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Errore durante la registrazione. Riprova più tardi.");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login-signup/signuperror.jsp");
            dispatcher.forward(request, response);
            return;
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login-signup/signupok.jsp");
        dispatcher.forward(request, response);
    }
    
    private String hashPassword(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] messageDigest = md.digest(password.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
        while (hashtext.length() < 64) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }
}