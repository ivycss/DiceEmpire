package diceempire.control;

import java.sql.SQLException;
import diceempire.model.*;

public interface CartModel {
 //verifica se il carrello ha elementi 0 o 1 se ha elementi 0 allora va creato nuovo e istanziata una nuova sessione
	
//se il carrello diventa 0 va cancellata la sessione

	// questo probabilemnte serve all'interno del carrello ma non qui, public Prodotto doRetrieveByKey(int code) throws SQLException;
	
	public Prodotto doRetrieveByKey(int code) throws SQLException; // questo va bene così com'è perché serve a visualizzare le cose nel carrello, va implementato con il concetto di sessione

}
