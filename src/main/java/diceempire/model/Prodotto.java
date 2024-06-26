package diceempire.model;
import java.io.Serializable;

public class Prodotto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private Integer id; 
	private String tipoProdotto;
	private String tipoGioco;
	private String tipoCarte;
	private Integer edizione;
	private String edizioneLimitata;
	private String produttore;
	private Double prezzo;
	private String descLunga;
	private String descCorta;
	private Integer eta;
	private Integer quantita;
	private String nomeImmagine;
	private Double IVA;



	public Integer getEta() {
		return eta;
	}



	public void setEta(Integer eta) {
		this.eta = eta;
	}



	public String getTipoProdotto() {
		return tipoProdotto;
	}



	public void setTipoProdotto(String tipoProdotto) {
		this.tipoProdotto = tipoProdotto;
	}



	public String getTipoGioco() {
		return tipoGioco;
	}



	public void setTipoGioco(String tipoGioco) {
		this.tipoGioco = tipoGioco;
	}



	public String getTipoCarte() {
		return tipoCarte;
	}



	public void setTipoCarte(String tipoCarte) {
		this.tipoCarte = tipoCarte;
	}



	public Integer getEdizione() {
		return edizione;
	}



	public void setEdizione(Integer edizione) {
		this.edizione = edizione;
	}



	public String getEdizioneLimitata() {
		return edizioneLimitata;
	}



	public void setEdizioneLimitata(String edizioneLimitata) {
		this.edizioneLimitata = edizioneLimitata;
	}



	public Double getPrezzo() {
		return prezzo;
	}



	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getProduttore() {
		return produttore;
	}



	public void setProduttore(String produttore) {
		this.produttore = produttore;
	}




	public String getDescCorta() {
		return descCorta;
	}



	public void setDescCorta(String descCorta) {
		this.descCorta = descCorta;
	}



	public Integer getQuantita() {
		return quantita;
	}



	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public String getNomeImmagine() {
		return nomeImmagine;
	}



	public void setNomeImmagine(String nomeImmagine) {
		this.nomeImmagine = nomeImmagine;
	}



	public String getDescLunga() {
		return descLunga;
	}



	public void setDescLunga(String descLunga) {
		this.descLunga = descLunga;
	}



	public Double getIVA() {
		return IVA;
	}



	public void setIVA(Double iVA) {
		IVA = iVA;
	}
	
	public double getPrezzoIVA() {
		Double prezzoIVA=(this.getPrezzo()*this.getIVA());
		return Math.round(prezzoIVA * 100.0) / 100.0;
		
	}



}

