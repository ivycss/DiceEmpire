package diceempire.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import diceempire.connection.DriverManagerConnection;
import diceempire.model.*;


public class UtenteModelMD implements UtenteModel{
	private static final String TABLE_NAME = "utente";


	@Override
	public synchronized void doSave(Utente user) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    String insertSQL = "INSERT INTO " + UtenteModelMD.TABLE_NAME
	            + " (cf, password, nome, cognome, eta , numeroTelefono, mail, citta, cap, via) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try {
	        connection = DriverManagerConnection.getConnection();
	        preparedStatement = connection.prepareStatement(insertSQL);

	        preparedStatement.setString(1, user.getCf());
	        preparedStatement.setString(2, user.getPassword());
	        preparedStatement.setString(3, user.getNome());
	        preparedStatement.setString(4, user.getCognome());
	        preparedStatement.setInt(5, user.getAge());
	        preparedStatement.setInt(6, user.getTelefono());
	        preparedStatement.setString(7, user.getMail());
	        preparedStatement.setString(8, user.getCitta());
	        preparedStatement.setInt(9, user.getCap());
	        preparedStatement.setString(10, user.getVia());


	        preparedStatement.executeUpdate();
	        connection.commit();

	    } finally {
	        try {
	            if (preparedStatement != null)
	                preparedStatement.close();
	        } finally {
	            DriverManagerConnection.releaseConnection(connection);
	        }
	    }
	}


	@Override
	public Utente Login(String mail, String password) throws SQLException {
		Utente user = null;
		String query; 
		PreparedStatement pst; 
		ResultSet rs;
		Connection con = null; 
		
		
		try {
			con = DriverManagerConnection.getConnection();
			query = "SELECT * FROM " + UtenteModelMD.TABLE_NAME + " WHERE mail = ?  AND password = ?";
			pst = con.prepareStatement(query);
			pst.setString(1,mail);
			pst.setString(2, password);
			
			rs = pst.executeQuery();
			
			while (rs.next()) {
				user = new Utente();
				user.setId(rs.getInt("idUtente"));
				user.setCf(rs.getString("cf"));
				user.setPassword(rs.getString("password"));
				user.setNome(rs.getString("nome"));
				user.setCognome(rs.getString("cognome"));
				user.setAge(rs.getInt("eta"));
				user.setTelefono(rs.getInt("numeroTelefono"));
				user.setMail(rs.getString("mail"));
				user.setCitta(rs.getString("citta"));
				user.setCap(rs.getInt("cap"));
				user.setVia(rs.getString("via"));
				user.setRole(rs.getString("role"));
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return user;
	}
	
	@Override
	public Utente doRetrieveByKey(String mail) throws SQLException {
	    Utente user = null;
	    String query;
	    PreparedStatement pst;
	    ResultSet rs;
	    Connection con = null;

	    try {
	        con = DriverManagerConnection.getConnection();
	        query = "SELECT * FROM " + UtenteModelMD.TABLE_NAME + " WHERE mail = ?";
	        pst = con.prepareStatement(query);
	        pst.setString(1, mail);

	        rs = pst.executeQuery();

	        if (rs.next()) {
				user = new Utente();
				user.setId(rs.getInt("idUtente"));
				user.setCf(rs.getString("cf"));
				user.setPassword(rs.getString("password"));
				user.setNome(rs.getString("nome"));
				user.setCognome(rs.getString("cognome"));
				user.setAge(rs.getInt("eta"));
				user.setTelefono(rs.getInt("numeroTelefono"));
				user.setMail(rs.getString("mail"));
				user.setCitta(rs.getString("citta"));
				user.setCap(rs.getInt("cap"));
				user.setVia(rs.getString("via"));
				user.setRole(rs.getString("role"));
	        }
	    } finally {
	        if (con != null) {
	            con.close();
	        }
	    }

	    return user;
	}

	@Override
	public  void doDelete(String mail) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    String deleteSQL = "DELETE FROM " + UtenteModelMD.TABLE_NAME + " WHERE mail = ?";

	    try {
	        connection = DriverManagerConnection.getConnection();
	        preparedStatement = connection.prepareStatement(deleteSQL);
	        preparedStatement.setString(1, mail);

	        preparedStatement.executeUpdate();
	        connection.commit();
	    } finally {
	        try {
	            if (preparedStatement != null)
	                preparedStatement.close();
	        } finally {
	            DriverManagerConnection.releaseConnection(connection);
	        }
	    }
	}

	@Override
	public  List<Utente> doRetrieveAll() throws SQLException {
	    List<Utente> userList = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;

	    String selectSQL = "SELECT * FROM " + UtenteModelMD.TABLE_NAME;

	    try {
	        connection = DriverManagerConnection.getConnection();
	        preparedStatement = connection.prepareStatement(selectSQL);
	        resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            Utente user = new Utente();
	            user.setId(resultSet.getInt("idUtente"));
	            user.setCf(resultSet.getString("cf"));
	            user.setPassword(resultSet.getString("password"));
	            user.setNome(resultSet.getString("nome"));
	            user.setCognome(resultSet.getString("cognome"));
	            user.setAge(resultSet.getInt("eta"));
	            user.setTelefono(resultSet.getInt("numeroTelefono"));
	            user.setMail(resultSet.getString("mail"));
	            user.setCitta(resultSet.getString("citta"));
	            user.setCap(resultSet.getInt("cap"));
	            user.setVia(resultSet.getString("via"));
	            user.setRole(resultSet.getString("role"));
	            userList.add(user);
	        }
	    } finally {
	        if (resultSet != null) {
	            resultSet.close();
	        }
	        if (preparedStatement != null) {
	            preparedStatement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }

	    return userList;
	}
	
	public synchronized Utente doUpdate(Utente user, String emailOld) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        connection = DriverManagerConnection.getConnection();
	        String sql = "UPDATE " + TABLE_NAME + " SET cf = ?, password = ?, nome = ?, cognome = ?, eta = ?, numeroTelefono = ?, mail = ?, citta = ?, cap = ?, via = ? WHERE mail = ?";

	        preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, user.getCf());
	        preparedStatement.setString(2, user.getPassword());
	        preparedStatement.setString(3, user.getNome());
	        preparedStatement.setString(4, user.getCognome());
	        preparedStatement.setInt(5, user.getAge());
	        preparedStatement.setInt(6, user.getTelefono());
	        preparedStatement.setString(7, user.getMail());
	        preparedStatement.setString(8, user.getCitta());
	        preparedStatement.setInt(9, user.getCap());
	        preparedStatement.setString(10, user.getVia());
	        preparedStatement.setString(11, emailOld);

	        preparedStatement.executeUpdate();
	        connection.commit();

	    } finally {
	        try {
	            if (preparedStatement != null)
	                preparedStatement.close();
	        } finally {
	            if (connection != null)
	                connection.close();
	        }
	    }

	    return user;
	}
}
