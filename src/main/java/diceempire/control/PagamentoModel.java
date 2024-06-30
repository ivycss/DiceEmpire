package diceempire.control;

import java.sql.SQLException;

import diceempire.model.Carta;

public interface PagamentoModel {

	public boolean doSave(Carta carta) throws SQLException;
	
	public Carta doRetrieveByUser(Integer idUtente) throws SQLException;
	
	public Carta doUpdate(Carta carta) throws SQLException;
	
	public boolean doDelete(int idUtente) throws SQLException;
	
}
