package diceempire.model;



public class ProdottoInCarrello extends Prodotto{

	private static final long serialVersionUID = 1L;
	private Prodotto p;
	  private int numItems;
	  
	  
	  public Prodotto getItem() {
		return p;
	}
	  
	  public ProdottoInCarrello(Prodotto item) {
		    setItem(item);
		    setNumItems(1);
		  }
	  
	public ProdottoInCarrello() {
		// TODO Auto-generated constructor stub
	}

	public void setItem(Prodotto item) {
		p = item;
	}
	public int getNumItems() {
		 return numItems;
	}
	public void setNumItems(int n) {
		numItems = n;
	}
	
	public void incrementaNumero() {
		setNumItems(getNumItems()+1);
		
	}
	
	public void decrementaNumero() {
	    setNumItems(getNumItems() - 1);
	}

	
	public String getNomeProdotto() {
		return p.getNome();
	}
	public Double getCosto() {
		return p.getPrezzoIVA();
	}
	

	// getImmagine
	public void EliminaElemento() {
		setNumItems(0);
		
	}
	//Costo totale di tutti gli elementi di quel tipo
	public Double CostoTotale() {
		return (getCosto()*getNumItems());
		
	}
	

}
