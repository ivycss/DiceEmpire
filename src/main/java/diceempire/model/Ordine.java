package diceempire.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Ordine extends Prodotto{

    private static final long serialVersionUID = 1L;
    private Integer idOrdine;
    private Integer idUtente;
    private Date dataOrdine;
    private List<ProdottoInCarrello> prodottiOrdine;



	public Ordine(List<ProdottoInCarrello> prodottiOrdine) {
		this.prodottiOrdine = prodottiOrdine;
	}

	public Integer getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(Integer idOrdine) {
        this.idOrdine = idOrdine;
    }

    
    public Integer getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}

	public Date getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public List<ProdottoInCarrello> getProdottiOrdine() {
        return prodottiOrdine;
    }

    public void setProdottiOrdine(List<ProdottoInCarrello> prodottiOrdine) {
        this.prodottiOrdine = prodottiOrdine;
    }

    public void addProdotto(ProdottoInCarrello prodotto) {
        this.prodottiOrdine.add(prodotto);
    }

    public int quantita(int id) {
        int quantita = 0;
        for (ProdottoInCarrello p : this.prodottiOrdine) {
            if (p.getItem().getId().equals(id)) {
                quantita++;
            }
        }
        return quantita;
    }
}
