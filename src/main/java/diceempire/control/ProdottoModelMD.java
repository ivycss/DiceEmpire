package diceempire.control;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import diceempire.model.*;
import diceempire.connection.*;

public class ProdottoModelMD implements ProdottoModel {

	private static final String TABLE_NAME = "prodotti";

	@Override
	public synchronized void doSave(Prodotto product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProdottoModelMD.TABLE_NAME
				+ " (nome, tipoProdotto, tipoGioco, tipoCarte, produttore, prezzo, descrizione, descrizioneDettagliata, eta, edizione, edizioneLimitata, quantita, iva, immagine) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnection.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getNome());
			preparedStatement.setString(2, product.getTipoProdotto());
			preparedStatement.setString(3, product.getTipoGioco());
			preparedStatement.setString(4, product.getTipoCarte());	
			preparedStatement.setString(5, product.getProduttore());
			preparedStatement.setDouble(6, product.getPrezzo());
			preparedStatement.setString(7, product.getDescCorta());
			preparedStatement.setString(8, product.getDescLunga());
			preparedStatement.setInt(9, product.getEta());
			preparedStatement.setInt(10, product.getEdizione());
			preparedStatement.setString(11, product.getEdizioneLimitata());
			preparedStatement.setInt(12, product.getQuantita());
			preparedStatement.setDouble(13, product.getIVA());
			preparedStatement.setBytes(14, product.getImmagine());

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
	public synchronized Prodotto doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Prodotto prodotto = new Prodotto();

		String selectSQL = "SELECT * FROM " + ProdottoModelMD.TABLE_NAME + " WHERE id = ?";

		try {
			connection = DriverManagerConnection.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				prodotto.setId(rs.getInt("id"));
				prodotto.setNome(rs.getString("nome"));
				prodotto.setDescCorta(rs.getString("descrizione"));
				prodotto.setDescLunga(rs.getString("descrizioneDettagliata"));
				prodotto.setPrezzo(rs.getDouble("prezzo"));
				prodotto.setQuantita(rs.getInt("quantita"));
				prodotto.setEdizione(rs.getInt("edizione"));
				prodotto.setEdizioneLimitata(rs.getString("edizioneLimitata"));
				prodotto.setTipoProdotto(rs.getString("tipoProdotto"));
				prodotto.setTipoGioco(rs.getString("tipoGioco"));
				prodotto.setTipoCarte(rs.getString("tipoCarte"));
				prodotto.setProduttore(rs.getString("produttore"));
				prodotto.setEta(rs.getInt("eta"));
				prodotto.setIVA(rs.getDouble("iva"));
				prodotto.setImmagine(rs.getBytes("immagine"));
				}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnection.releaseConnection(connection);
			}
		}
		return prodotto;
	}

	@Override
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProdottoModelMD.TABLE_NAME + " WHERE id = ?";

		try {
			connection = DriverManagerConnection.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();
			connection.commit();
		} finally {
		    try {
		        if (preparedStatement != null)
		            preparedStatement.close();
		    } finally {
		        if (connection != null)
		            DriverManagerConnection.releaseConnection(connection);
		    }
		}

		return (result != 0);
	}

	@Override
	public synchronized Collection<Prodotto> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Prodotto> products = new LinkedList<Prodotto>();

		String selectSQL = "SELECT * FROM " + ProdottoModelMD.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnection.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				Prodotto prodotto = new Prodotto();

					prodotto.setId(rs.getInt("id"));
					prodotto.setNome(rs.getString("nome"));
					prodotto.setDescCorta(rs.getString("descrizione"));
					prodotto.setDescLunga(rs.getString("descrizioneDettagliata"));
					prodotto.setPrezzo(rs.getDouble("prezzo"));
					prodotto.setQuantita(rs.getInt("quantita"));
					prodotto.setEdizione(rs.getInt("edizione"));
					prodotto.setEdizioneLimitata(rs.getString("edizioneLimitata"));
					prodotto.setTipoProdotto(rs.getString("tipoProdotto"));
					prodotto.setTipoGioco(rs.getString("tipoGioco"));
					prodotto.setTipoCarte(rs.getString("tipoCarte"));
					prodotto.setProduttore(rs.getString("produttore"));
					prodotto.setEta(rs.getInt("eta"));
					prodotto.setIVA(rs.getDouble("iva"));
					prodotto.setImmagine(rs.getBytes("immagine"));
					products.add(prodotto);
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnection.releaseConnection(connection);
			}
		}
		return products;
	}
	
	public synchronized Collection<Prodotto> doRetrieveByCategory(String category) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Collection<Prodotto> products = new LinkedList<Prodotto>();

        String selectSQL = "SELECT * FROM " + ProdottoModelMD.TABLE_NAME + " WHERE tipoProdotto = ?";

        try {
            connection = DriverManagerConnection.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, category);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Prodotto prodotto = new Prodotto();
                prodotto.setId(rs.getInt("id"));
                prodotto.setNome(rs.getString("nome"));
                prodotto.setDescCorta(rs.getString("descrizione"));
                prodotto.setDescLunga(rs.getString("descrizioneDettagliata"));
                prodotto.setPrezzo(rs.getDouble("prezzo"));
                prodotto.setQuantita(rs.getInt("quantita"));
                prodotto.setEdizione(rs.getInt("edizione"));
                prodotto.setEdizioneLimitata(rs.getString("edizioneLimitata"));
                prodotto.setTipoProdotto(rs.getString("tipoProdotto"));
                prodotto.setTipoGioco(rs.getString("tipoGioco"));
                prodotto.setTipoCarte(rs.getString("tipoCarte"));
                prodotto.setProduttore(rs.getString("produttore"));
                prodotto.setEta(rs.getInt("eta"));
                prodotto.setIVA(rs.getDouble("iva"));
                prodotto.setImmagine(rs.getBytes("immagine"));
                		products.add(prodotto);
            }
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnection.releaseConnection(connection);
            }
        }
        return products;
    }

	@Override
	public void doUpdate(Prodotto product, Integer id) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    String updateSQL = "UPDATE " + TABLE_NAME + " SET nome = ?, tipoProdotto = ?, tipoGioco = ?, tipoCarte = ?, produttore = ?, prezzo = ?, descrizione = ?, descrizioneDettagliata = ?, eta = ?, edizione = ?, edizioneLimitata = ?, quantita = ?, iva = ?";

	    
	    if (product.getImmagine() != null && product.getImmagine().length > 0) {
	        updateSQL += ", immagine = ?";
	    }

	    updateSQL += " WHERE id = ?";

	    try {
	        connection = DriverManagerConnection.getConnection();
	        connection.setAutoCommit(false); 
	        preparedStatement = connection.prepareStatement(updateSQL);

	        preparedStatement.setString(1, product.getNome());
	        preparedStatement.setString(2, product.getTipoProdotto());
	        preparedStatement.setString(3, product.getTipoGioco());
	        preparedStatement.setString(4, product.getTipoCarte());
	        preparedStatement.setString(5, product.getProduttore());
	        preparedStatement.setDouble(6, product.getPrezzo());
	        preparedStatement.setString(7, product.getDescCorta());
	        preparedStatement.setString(8, product.getDescLunga());
	        preparedStatement.setInt(9, product.getEta());
	        preparedStatement.setInt(10, product.getEdizione());
	        preparedStatement.setString(11, product.getEdizioneLimitata());
	        preparedStatement.setInt(12, product.getQuantita());
	        preparedStatement.setDouble(13, product.getIVA());

	        //
	        int parameterIndex = 14;
	        if (product.getImmagine() != null && product.getImmagine().length > 0) {
	            preparedStatement.setBytes(parameterIndex++, product.getImmagine());
	        }

	        preparedStatement.setInt(parameterIndex, id);

	        int rowsAffected = preparedStatement.executeUpdate(); 

	        connection.commit();

	        if (rowsAffected > 0) {
	            System.out.println("Aggiornamento completato per l'id " + id);
	        } else {
	            System.out.println("Nessuna riga aggiornata per l'id " + id);
	        }

	    } catch (SQLException e) {
	        if (connection != null) {
	            try {
	                connection.rollback(); 
	            } catch (SQLException rollbackEx) {
	                System.out.println("Rollback fallito: " + rollbackEx.getMessage());
	            }
	        }
	        throw e; 
	    } finally {
	        if (preparedStatement != null) {
	            preparedStatement.close();
	        }
	        if (connection != null) {
	            connection.setAutoCommit(true); 
	            connection.close();
	        }
	    }
	}
	
	public synchronized Collection<Prodotto> doRetrieveByQuery(String query) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    Collection<Prodotto> products = new LinkedList<Prodotto>();
	    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE nome LIKE ? OR descrizione LIKE ?";

	    try {
	        connection = DriverManagerConnection.getConnection();
	        preparedStatement = connection.prepareStatement(selectSQL);
	        preparedStatement.setString(1, "%" + query + "%");
	        preparedStatement.setString(2, "%" + query + "%");

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            Prodotto prodotto = new Prodotto();
	            prodotto.setId(rs.getInt("id"));
	            prodotto.setNome(rs.getString("nome"));
	            prodotto.setDescCorta(rs.getString("descrizione"));
	            prodotto.setDescLunga(rs.getString("descrizioneDettagliata"));
	            prodotto.setPrezzo(rs.getDouble("prezzo"));
	            prodotto.setQuantita(rs.getInt("quantita"));
	            prodotto.setEdizione(rs.getInt("edizione"));
	            prodotto.setEdizioneLimitata(rs.getString("edizioneLimitata"));
	            prodotto.setTipoProdotto(rs.getString("tipoProdotto"));
	            prodotto.setTipoGioco(rs.getString("tipoGioco"));
	            prodotto.setTipoCarte(rs.getString("tipoCarte"));
	            prodotto.setProduttore(rs.getString("produttore"));
	            prodotto.setEta(rs.getInt("eta"));
	            prodotto.setIVA(rs.getDouble("iva"));
	            prodotto.setImmagine(rs.getBytes("immagine"));
	            products.add(prodotto);
	        }
	    } finally {
	        try {
	            if (preparedStatement != null)
	                preparedStatement.close();
	        } finally {
	            DriverManagerConnection.releaseConnection(connection);
	        }
	    }
	    return products;
	}

}

