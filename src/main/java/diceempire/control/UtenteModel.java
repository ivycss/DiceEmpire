package diceempire.control;

import java.sql.SQLException;
import java.util.List;

import diceempire.model.Utente;

public interface UtenteModel{

	public Utente Login(String nick, String Password) throws SQLException;

	public void doSave(Utente user) throws SQLException;
	
	public Utente doRetrieveByKey(String mail) throws SQLException;
	
	public  void doDelete(String mail) throws SQLException;
	
	public  List<Utente> doRetrieveAll() throws SQLException;
	
	public  Utente doUpdate(Utente user, String emailOld) throws SQLException;
	
	

}
