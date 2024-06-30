package diceempire.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import diceempire.model.Utente;
import diceempire.control.UtenteModelMD;

@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
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
        response.sendRedirect("login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if ("update".equals(action)) {
            handleUpdate(request, response);
        } else {
            handleLogin(request, response);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String password = request.getParameter("password");
            String psw = hashPassword(password);
            String mail = request.getParameter("mail");

            // Controlla se mail o password sono vuoti
            if (mail == null || password == null || mail.isEmpty() || password.isEmpty()) {
                response.sendRedirect("login-signup/loginempty.jsp");
                return;
            }

            // Verifica le credenziali dell'utente
            Utente utente = model.Login(mail, psw);

            if (utente != null) {
                // Autenticazione riuscita, salva le informazioni dell'utente nella sessione
            	//la divisione è fatta per semplificare i vari impieghi che ne devono essere fatti 
                HttpSession session = request.getSession();
                session.setAttribute("auth", utente);
                session.setAttribute("userRole", utente.getRole());
                session.setAttribute("email", utente.getMail());
                session.setAttribute("idUtente", utente.getId());
                response.sendRedirect("login-signup/loginok.jsp?nome=" + utente.getNome());
            } else {
                // Credenziali non valide
                response.sendRedirect("login-signup/loginusererror.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        
            response.sendRedirect("login-signup/loginerror.jsp");
        }
    }

    private void handleUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("auth") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Utente oldUser = (Utente) session.getAttribute("auth");

        try {
            String cf = request.getParameter("cf");
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            int age = Integer.parseInt(request.getParameter("age"));
            int telefono = Integer.parseInt(request.getParameter("telefono"));
            String mail = request.getParameter("mail");
            String citta = request.getParameter("citta");
            int cap = Integer.parseInt(request.getParameter("cap"));
            String via = request.getParameter("via");
            // Aggiorna le informazioni dell'utente
            Utente newUser = new Utente();
            newUser.setId(oldUser.getId());
            newUser.setCf(cf);
            newUser.setPassword(oldUser.getPassword());
            newUser.setNome(nome);
            newUser.setCognome(cognome);
            newUser.setAge(age);
            newUser.setTelefono(telefono);
            newUser.setMail(mail);
            newUser.setCitta(citta);
            newUser.setCap(cap);
            newUser.setVia(via);
            newUser.setRole(oldUser.getRole());

            model.doUpdate(newUser, oldUser.getMail());

            // Aggiorna l'utente nella sessione
            session.setAttribute("auth", newUser);

            response.sendRedirect("userinfo.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("update-error.jsp");
        }
    }
    
    private String hashPassword(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256"); //tipologia di hash scelta
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
