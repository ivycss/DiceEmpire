package diceempire.model;

public class Carta {
	
	private Long numeroCarta;
	private Integer dataScadenza;
	private Integer cvc;
	private String nomeInt;
	private String CognomeInt;
	private Integer idUtente;
	
	
	
	public Long getNumeroCarta() {
		return numeroCarta;
	}
	public void setNumeroCarta(Long numeroCarta) {
		this.numeroCarta = numeroCarta;
	}
	public Integer getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(Integer dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public Integer getCvc() {
		return cvc;
	}
	public void setCvc(Integer cvc) {
		this.cvc = cvc;
	}
	public String getNomeInt() {
		return nomeInt;
	}
	public void setNomeInt(String nomeInt) {
		this.nomeInt = nomeInt;
	}
	public String getCognomeInt() {
		return CognomeInt;
	}
	public void setCognomeInt(String cognomeInt) {
		CognomeInt = cognomeInt;
	}
	public Integer getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}

}
