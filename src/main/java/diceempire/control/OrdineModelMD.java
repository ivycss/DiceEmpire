package diceempire.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import diceempire.connection.DriverManagerConnection;
import diceempire.model.Ordine;
import diceempire.model.Prodotto;
import diceempire.servlet.*;

public class OrdineModelMD implements OrdineModel {
    private static final String TABLE_NAME = "ordine";

    @Override
    public Boolean doSave(Integer idUtente) throws SQLException {
        final String INSERT_SQL = "INSERT INTO " + OrdineModelMD.TABLE_NAME + " (idUtente, dataOrdine) VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Boolean good = true;
        
        // Assuming dataOrdine is the current timestamp
        Timestamp dataOrdine = new Timestamp(System.currentTimeMillis());

        try {
            System.out.println("Connecting to database...");
            connection = DriverManagerConnection.getConnection();
            connection.setAutoCommit(false); // Begin transaction

            System.out.println("Preparing statement...");
            preparedStatement = connection.prepareStatement(INSERT_SQL);
            preparedStatement.setInt(1, idUtente);
            preparedStatement.setTimestamp(2, dataOrdine);
            

            System.out.println("Executing update...");
            int affectedRows = preparedStatement.executeUpdate();

            System.out.println("Affected rows: " + affectedRows);
            if (affectedRows == 0) {
                good = false;
                throw new SQLException("Creating order failed, no rows affected.");
            }

            connection.commit(); // Commit transaction

            System.out.println("Order saved successfully.");

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    System.err.println("Transaction is being rolled back.");
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            good = false;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.setAutoCommit(true); // Reset auto-commit to default
                DriverManagerConnection.releaseConnection(connection);
            }
        }
        return good;
    }

    @Override
    public Ordine doRetrieveByKey(Integer idOrdine) throws SQLException {
        Connection connection = null;
		PreparedStatement preparedStatement = null;

		Ordine ordine = new Ordine(null);

		String selectSQL = "SELECT * FROM " + OrdineModelMD.TABLE_NAME + " WHERE idOrdine = ?";

		try {
			connection = DriverManagerConnection.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idOrdine);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ordine.setIdOrdine(rs.getInt("idOrdine"));
				ordine.setIdUtente(rs.getInt("idUtente"));
				ordine.setDataOrdine(rs.getDate("dataOrdine"));
				}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnection.releaseConnection(connection);
			}
		}
		System.out.println("retrieve di ordinemodel:");
		System.out.println(ordine.getIdOrdine());
		System.out.println(ordine.getIdUtente());
		System.out.println(ordine.getDataOrdine());
		return ordine;
	}
    
    
    @Override
    public Ordine doRetrieveByUser(Integer idUtente) throws SQLException {
        Connection connection = null;
		PreparedStatement preparedStatement = null;

		Ordine ordine = new Ordine(null);

		String selectSQL = "SELECT * FROM " + OrdineModelMD.TABLE_NAME + " WHERE idUtente = ?";

		try {
			connection = DriverManagerConnection.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idUtente);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ordine.setIdOrdine(rs.getInt("idOrdine"));
				ordine.setIdUtente(rs.getInt("idUtente"));
				ordine.setDataOrdine(rs.getDate("dataOrdine"));
				}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnection.releaseConnection(connection);
			}
		}
		System.out.println("retrieve di ordinemodel:");
		System.out.println(ordine.getIdOrdine());
		System.out.println(ordine.getIdUtente());
		System.out.println(ordine.getDataOrdine());
		return ordine;
	}
    
    public List<Ordine> doRetrieveAllByUser(Integer idUtente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Ordine> ordini = new ArrayList<>();

        String selectSQL = "SELECT * FROM " + OrdineModelMD.TABLE_NAME + " WHERE idUtente = ?";

        try {
            connection = DriverManagerConnection.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idUtente);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Ordine ordine = new Ordine(null);
                ordine.setIdOrdine(rs.getInt("idOrdine"));
                ordine.setIdUtente(rs.getInt("idUtente"));
                ordine.setDataOrdine(rs.getDate("dataOrdine"));
                ordini.add(ordine);
            }
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnection.releaseConnection(connection);
            }
        }
        return ordini;
    }

    public List<Ordine> doRetrieveAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Ordine> ordini = new ArrayList<>();

        String selectSQL = "SELECT * FROM " + OrdineModelMD.TABLE_NAME;

        try {
            connection = DriverManagerConnection.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Ordine ordine = new Ordine(null);
                ordine.setIdOrdine(rs.getInt("idOrdine"));
                ordine.setIdUtente(rs.getInt("idUtente"));
                ordine.setDataOrdine(rs.getDate("dataOrdine"));
                ordini.add(ordine);
            }
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnection.releaseConnection(connection);
            }
        }
        return ordini;
    }
    
    public List<Ordine> doRetrieveAllByDateRange(Date startDate, Date endDate) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Ordine> ordini = new ArrayList<>();

        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE dataOrdine BETWEEN ? AND ?";

        try {
            connection = DriverManagerConnection.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setTimestamp(1, new Timestamp(startDate.getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(endDate.getTime()));
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Ordine ordine = new Ordine(null);
                ordine.setIdOrdine(rs.getInt("idOrdine"));
                ordine.setIdUtente(rs.getInt("idUtente"));
                ordine.setDataOrdine(rs.getDate("dataOrdine"));
                ordini.add(ordine);
            }
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            DriverManagerConnection.releaseConnection(connection);
        }
        return ordini;
    }
    
    public List<Ordine> doRetrieveAllSortedByIdUtente() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Ordine> ordini = new ArrayList<>();

        String selectSQL = "SELECT * FROM " + TABLE_NAME + " ORDER BY idUtente";

        try {
            connection = DriverManagerConnection.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Ordine ordine = new Ordine(null);
                ordine.setIdOrdine(rs.getInt("idOrdine"));
                ordine.setIdUtente(rs.getInt("idUtente"));
                ordine.setDataOrdine(rs.getDate("dataOrdine"));
                ordini.add(ordine);
            }
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            DriverManagerConnection.releaseConnection(connection);
        }
        return ordini;
    }
}
