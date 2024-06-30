package diceempire.control;

import java.sql.SQLException;
import java.util.Collection;
import diceempire.model.*;

public interface ProdottoModel {
	public void doSave(Prodotto product) throws SQLException;

	public boolean doDelete(int code) throws SQLException;

	public Prodotto doRetrieveByKey(int code) throws SQLException;
	
	public void doUpdate(Prodotto product, Integer id) throws SQLException;
	
	public Collection<Prodotto> doRetrieveAll(String order) throws SQLException;
	
	public Collection<Prodotto> doRetrieveByCategory(String category) throws SQLException;
	
	public  Collection<Prodotto> doRetrieveByQuery(String query) throws SQLException;
}
