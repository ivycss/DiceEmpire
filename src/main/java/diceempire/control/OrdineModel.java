package diceempire.control;

import java.sql.SQLException;
import java.util.List;

import diceempire.model.Ordine;
import diceempire.model.Utente;

public interface OrdineModel {
	
	public Boolean doSave(Integer idUtente) throws SQLException;
	
	public Ordine doRetrieveByKey(Integer idOrdine) throws SQLException; 
	
	public Ordine doRetrieveByUser(Integer idUtente) throws SQLException; 
	
	public List<Ordine> doRetrieveAllByUser(Integer idUtente) throws SQLException;
}
