package diceempire.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import diceempire.connection.DriverManagerConnection;
import diceempire.model.Ordine;
import diceempire.model.ProdottoInCarrello;

public class OrdineDettagliModelMD implements OrdineDettagliModel {
    private static final String TABLE_NAME = "dettagliordine";

    public Boolean doSave(Ordine ordine) throws SQLException {
        final String INSERT_SQL = "INSERT INTO " + OrdineDettagliModelMD.TABLE_NAME + "(idOrdine, nome, quantita, descrizione, foto, prezzoIva, totale, iva) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Boolean good = true;

        try {
            connection = DriverManagerConnection.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_SQL);
            if(ordine.getProdottiOrdine()==null) {
            	System.out.println("Il problema è qua ORDINEDETTAGLIMODELMD");
            }
            ArrayList<ProdottoInCarrello> ProdottiTemp = (ArrayList<ProdottoInCarrello>) ordine.getProdottiOrdine();

            for (ProdottoInCarrello Tp : ProdottiTemp) {
            	System.out.println("id prodotto" + Tp.getItem().getId());
                if (Tp != null && Tp.getItem().getId() != null) { // Controlla che Tp e il suo ID non siano nulli
                    preparedStatement.setInt(1, ordine.getIdOrdine());
                    preparedStatement.setString(2, Tp.getItem().getNome());
                    preparedStatement.setInt(3, ordine.quantita(Tp.getItem().getId())); // Assicurati che questo metodo restituisca la quantit� corretta
                    preparedStatement.setString(4, Tp.getItem().getDescCorta());
                    preparedStatement.setString(5, Tp.getItem().getNomeImmagine());
                    preparedStatement.setDouble(6, Tp.getItem().getPrezzo());
                    preparedStatement.setDouble(7, Tp.getItem().getPrezzo());
                    preparedStatement.setDouble(8, Tp.getItem().getIVA());
                    preparedStatement.executeUpdate();
                } else {
                    System.out.println("ProdottoInCarrello nullo o ID nullo trovato");
                }
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            DriverManagerConnection.releaseConnection(connection);
        }
        return good;
    }
    
    public Ordine doRetrieveByKey(int idOrdine) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Ordine ordine = new Ordine(null );
        String selectSQL = "SELECT * FROM " + OrdineDettagliModelMD.TABLE_NAME + " WHERE id = ?";

        try {
            connection = DriverManagerConnection.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idOrdine);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                // Popola l'arrayList di prodotti dell'ordine
                ProdottoInCarrello prodotto = new ProdottoInCarrello();
                prodotto.setId(rs.getInt("prodotto_id"));
                prodotto.setNumItems(rs.getInt("quantita"));
                prodotto.setPrezzo(rs.getDouble("prezzo"));
                prodotto.setDescCorta(rs.getString("desccorta"));
                prodotto.setIVA(rs.getDouble("iva"));
                prodotto.setNomeImmagine(rs.getString("foto"));
                prodotto.setNome(rs.getString("nomeProdotto"));
                ordine.getProdottiOrdine().add(prodotto);
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            DriverManagerConnection.releaseConnection(connection);
        }
        return ordine;
    }
}
