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
            connection.setAutoCommit(false); // disabilita l'autocommit 
            preparedStatement = connection.prepareStatement(INSERT_SQL);
            preparedStatement.setLong(1, carta.getNumeroCarta());
            preparedStatement.setInt(2, carta.getDataScadenza());
            preparedStatement.setInt(3, carta.getCvc());
            preparedStatement.setString(4, carta.getNomeInt());
            preparedStatement.setString(5, carta.getCognomeInt());
            preparedStatement.setInt(6, carta.getIdUtente());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                connection.commit(); // fai commit
                success = true;
                System.out.println("Informazioni di pagamento salvate correttamente.");
            } else {
                connection.rollback(); // annulla in caso di errore
                System.out.println("Salvataggio delle informazioni di pagamento fallito.");
            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Annulla in caso di eccezione
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.setAutoCommit(true); // ripristina l'autocommit
                DriverManagerConnection.releaseConnection(connection);
            }
        }
        return success;
    }
    
    @Override
    public Carta doRetrieveByUser(Integer idUtente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String SELECT_SQL = "SELECT * FROM " + TABLE_NAME + " WHERE idUtente = ?";

        try {
            connection = DriverManagerConnection.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_SQL);
            preparedStatement.setInt(1, idUtente);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Carta carta = new Carta();
                carta.setNumeroCarta(resultSet.getLong("numeroCarta"));
                carta.setDataScadenza(resultSet.getInt("dataScadenza"));
                carta.setCvc(resultSet.getInt("cvc"));
                carta.setNomeInt(resultSet.getString("nomeInt"));
                carta.setCognomeInt(resultSet.getString("cognomeInt"));
                carta.setIdUtente(resultSet.getInt("idUtente"));
                return carta;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                DriverManagerConnection.releaseConnection(connection);
            }
        }
        return null;
    }

    public Carta doUpdate(Carta carta) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String UPDATE_SQL = "UPDATE " + TABLE_NAME + " SET numeroCarta = ?, dataScadenza = ?, cvc = ?, nomeInt = ?, cognomeInt = ? WHERE idUtente = ?";

        try {
            connection = DriverManagerConnection.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setLong(1, carta.getNumeroCarta());
            preparedStatement.setInt(2, carta.getDataScadenza());
            preparedStatement.setInt(3, carta.getCvc());
            preparedStatement.setString(4, carta.getNomeInt());
            preparedStatement.setString(5, carta.getCognomeInt());
            preparedStatement.setInt(6, carta.getIdUtente());

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                connection.commit();
                System.out.println("Informazioni di pagamento aggiornate correttamente.");
            } else {
                connection.rollback(); 
                System.out.println("Aggiornamento delle informazioni di pagamento fallito.");
            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); 
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.setAutoCommit(true); 
                DriverManagerConnection.releaseConnection(connection);
            }
        }
        return carta;
    }

    public boolean doDelete(int idUtente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean success = false;

        String DELETE_SQL = "DELETE FROM " + TABLE_NAME + " WHERE idUtente = ?";

        try {
            connection = DriverManagerConnection.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_SQL);
            preparedStatement.setInt(1, idUtente);

            int rowsDeleted = preparedStatement.executeUpdate();
			connection.commit();
            if (rowsDeleted > 0) {
                success = true;
                System.out.println("Informazioni di pagamento eliminate correttamente.");
            } else {
                System.out.println("Eliminazione delle informazioni di pagamento fallita.");
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
    
}
