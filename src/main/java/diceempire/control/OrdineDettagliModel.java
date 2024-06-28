package diceempire.control;

import java.sql.SQLException;

import diceempire.model.Ordine;

public interface OrdineDettagliModel {
	
	public Boolean doSave(Ordine ordine) throws SQLException;
	
	public Ordine doRetrieveByKey(int code) throws SQLException; 
}
