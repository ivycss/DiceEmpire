package diceempire.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import diceempire.connection.DriverManagerConnection;
import diceempire.model.*;

public class PagamentoModelMD implements PagamentoModel{
    private static final String TABLE_NAME = "pagamento";

    public boolean doSave(Carta carta) throws SQLException {
        boolean success = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String INSERT_SQL = "INSERT INTO " + TABLE_NAME + " (numeroCarta, dataScadenza, cvc, nomeInt, cognomeInt, idUtente) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            connection = DriverManagerConnection.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_SQL);
            preparedStatement.setLong(1, carta.getNumeroCarta());
            preparedStatement.setInt(2, carta.getDataScadenza());
            preparedStatement.setInt(3, carta.getCvc());
            preparedStatement.setString(4, carta.getNomeInt());
            preparedStatement.setString(5, carta.getCognomeInt());
            preparedStatement.setInt(6, carta.getIdUtente());
            
            int rowsInserted = preparedStatement.executeUpdate();
            
            if (rowsInserted > 0) {
                success = true;
                System.out.println("Informazioni di pagamento salvate correttamente.");
            } else {
                System.out.println("Salvataggio delle informazioni di pagamento fallito.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                DriverManagerConnection.releaseConnection(connection);
            }
        }
        return success;
    }
    
    @Override
    public Carta doRetrieveByUser(Integer idUtente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Carta carta = null;

        try {
            connection = DriverManagerConnection.getConnection();
            String selectSQL = "SELECT * FROM " + PagamentoModelMD.TABLE_NAME + " WHERE idUtente = ?";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idUtente);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                carta = new Carta();
                carta.setNumeroCarta(rs.getLong("numeroCarta"));
                carta.setDataScadenza(rs.getInt("dataScadenza"));
                carta.setCvc(rs.getInt("cvc"));
                carta.setNomeInt(rs.getString("nomeInt"));
                carta.setCognomeInt(rs.getString("cognomeInt"));
                carta.setIdUtente(rs.getInt("idUtente"));
            } else {
                carta = null;
            }

        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnection.releaseConnection(connection);
            }
        }

        return carta;
    }

    public Carta doUpdate(Carta carta) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String UPDATE_SQL = "UPDATE " + TABLE_NAME + " SET numeroCarta = ?, dataScadenza = ?, cvc = ?, nomeInt = ?, cognomeInt = ? WHERE idUtente = ?";

        try {
            connection = DriverManagerConnection.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setLong(1, carta.getNumeroCarta());
            preparedStatement.setInt(2, carta.getDataScadenza());
            preparedStatement.setInt(3, carta.getCvc());
            preparedStatement.setString(4, carta.getNomeInt());
            preparedStatement.setString(5, carta.getCognomeInt());
            preparedStatement.setInt(6, carta.getIdUtente());

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Informazioni di pagamento aggiornate correttamente.");
            } else {
                System.out.println("Aggiornamento delle informazioni di pagamento fallito.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                DriverManagerConnection.releaseConnection(connection);
            }
        }
        return carta;
    }

    
}
