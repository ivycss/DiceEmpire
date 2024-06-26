package diceempire.control;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import diceempire.connection.DriverManagerConnection;
import diceempire.model.Cart;
import diceempire.model.Prodotto;
import diceempire.model.ProdottoInCarrello;

public class CartModelMD implements CartModel {

    private static final String TABLE_NAME = "prodotti";

    @Override
    public synchronized Prodotto doRetrieveByKey(int code) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Prodotto prodotto = new Prodotto();

        String selectSQL = "SELECT * FROM " + CartModelMD.TABLE_NAME + " WHERE id = ?";

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
                prodotto.setNomeImmagine(rs.getString("nomeImmagine"));
                prodotto.setEdizione(rs.getInt("edizione"));
                prodotto.setEdizioneLimitata(rs.getString("edizioneLimitata"));
                prodotto.setTipoProdotto(rs.getString("tipoProdotto"));
                prodotto.setTipoGioco(rs.getString("tipoGioco"));
                prodotto.setTipoCarte(rs.getString("tipoCarte"));
                prodotto.setProduttore(rs.getString("produttore"));
                prodotto.setEta(rs.getInt("eta"));
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

    public double getTotalCartPrice(Cart cartList) throws SQLException {
        Connection connection = null;
        double sum = 0;
        try {
            if (cartList.getProdottiCarrello().size() > 0) {
                for (ProdottoInCarrello item : cartList.getProdottiCarrello()) {
                    String query = "SELECT prezzo FROM prodotti WHERE id=?";
                    connection = DriverManagerConnection.getConnection();
                    PreparedStatement pst = connection.prepareStatement(query);
                    pst.setInt(1, item.getItem().getId());
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        sum += rs.getDouble("prezzo") * item.getNumItems();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            DriverManagerConnection.releaseConnection(connection);
        }
        return sum;
    }

    public ArrayList<Prodotto> getCartProducts(Cart cartList) throws SQLException {
        ArrayList<Prodotto> products = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DriverManagerConnection.getConnection();
            if (cartList.getProdottiCarrello().size() > 0) {
                for (ProdottoInCarrello item : cartList.getProdottiCarrello()) {
                    String query = "SELECT * FROM prodotti WHERE id=?";
                    PreparedStatement pst = connection.prepareStatement(query);
                    pst.setInt(1, item.getItem().getId());
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        Prodotto prodotto = new Prodotto();
                        prodotto.setId(rs.getInt("id"));
                        prodotto.setNome(rs.getString("nome"));
                        prodotto.setDescCorta(rs.getString("descrizione"));
                        prodotto.setDescLunga(rs.getString("descrizioneDettagliata"));
                        prodotto.setPrezzo(rs.getDouble("prezzo"));
                        prodotto.setQuantita(rs.getInt("quantita"));
                        prodotto.setNomeImmagine(rs.getString("nomeImmagine"));
                        prodotto.setEdizione(rs.getInt("edizione"));
                        prodotto.setEdizioneLimitata(rs.getString("edizioneLimitata"));
                        prodotto.setTipoProdotto(rs.getString("tipoProdotto"));
                        prodotto.setTipoGioco(rs.getString("tipoGioco"));
                        prodotto.setTipoCarte(rs.getString("tipoCarte"));
                        prodotto.setProduttore(rs.getString("produttore"));
                        prodotto.setEta(rs.getInt("eta"));
                        products.add(prodotto);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            DriverManagerConnection.releaseConnection(connection);
        }
        return products;
    }
}
