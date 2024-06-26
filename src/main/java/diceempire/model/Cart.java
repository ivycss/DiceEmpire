package diceempire.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<ProdottoInCarrello> prodottiCarrello;

    public Cart() {
        prodottiCarrello = new ArrayList<>();
    }

    public List<ProdottoInCarrello> getProdottiCarrello() {
        return prodottiCarrello;
    }

    public void setProdottiCarrello(List<ProdottoInCarrello> prodottiCarrello) {
        this.prodottiCarrello = prodottiCarrello;
    }

    public void addProduct(Prodotto prodotto) {
        for (ProdottoInCarrello item : prodottiCarrello) {
            if (item.getItem().getId() == prodotto.getId()) {
                item.incrementaNumero();
                return;
            }
        }
        prodottiCarrello.add(new ProdottoInCarrello(prodotto));
    }

    public void removeProduct(int id) {
        prodottiCarrello.removeIf(item -> item.getItem().getId() == id);
    }

    public void removeProdottiCarrello(int productId) {
        for (int i = 0; i < prodottiCarrello.size(); i++) {
            if (prodottiCarrello.get(i).getItem().getId() == productId) {
                prodottiCarrello.remove(i);
                break;
            }
        }
    }
    
    public double getTotalPrice() {
        double total = 0.0;
        for (ProdottoInCarrello item : prodottiCarrello) {
            total += item.CostoTotale();
        }
        return total;
    }
}
